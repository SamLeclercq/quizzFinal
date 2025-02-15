package com.worldline.quiz.core

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import com.worldline.quiz.ui.*

fun buildAdjacencyList(edges: List<Pair<Int, Int>>, numberOfNodes: Int): List<Set<Int>> {
    val adjacency = List(numberOfNodes) { mutableSetOf<Int>() }
    edges.forEach { (a, b) ->
        adjacency[a].add(b)
        adjacency[b].add(a)
    }
    return adjacency.map { it.toSet() }
}

@Composable
fun App() {
    val navController = rememberNavController()
    var showConfirmQuit by remember { mutableStateOf(false) }

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    MaterialTheme(colors = darkColors()) {
        Box(modifier = Modifier.fillMaxSize()) {
            NavHost(
                navController = navController,
                startDestination = "start",
                modifier = Modifier.fillMaxSize()
            ) {
                composable("start") {
                    StartScreen {
                        GameState.reset()
                        navController.navigate("map")
                    }
                }

                composable("map") {
                    MapScreen(
                        nodeStates = GameState.nodeStates,
                        onNodeClicked = { nodeId ->
                            navController.navigate("question/$nodeId")
                        },
                        onBossClicked = {
                            navController.navigate("boss")
                        }
                    )
                }

                composable("question/{nodeId}") { backStackEntry ->
                    val nodeId = backStackEntry.arguments?.getString("nodeId")?.toIntOrNull() ?: 0
                    QuestionScreen(
                        nodeId = nodeId,
                        onAnswerCorrect = {
                            val positions = naturalNodePositions()
                            val edges = naturalEdges()
                            val adjacency = buildAdjacencyList(edges, positions.size)
                            val currentY = positions[nodeId].second

                            GameState.nodeStates = GameState.nodeStates.mapIndexed { index, state ->
                                when {
                                    index == nodeId -> NodeState.SELECTED
                                    state == NodeState.SELECTED -> NodeState.SELECTED
                                    adjacency[nodeId].contains(index) && positions[index].second < currentY ->
                                        NodeState.AVAILABLE
                                    else -> NodeState.LOCKED
                                }
                            }
                            GameState.score++
                            navController.navigate("map")
                        },
                        onAnswerWrong = {
                            navController.navigate("gameover")
                        }
                    )
                }

                composable("boss") {
                    BossScreen(
                        onBossDefeated = {
                            navController.navigate("victory")
                        },
                        onGameOver = {
                            navController.navigate("gameover")
                        }
                    )
                }

                composable("victory") {
                    VictoryScreen(
                        onContinue = {
                            GameState.resetForNewRun()
                            navController.navigate("map")
                        },
                        onQuit = {
                            GameState.reset()
                            navController.navigate("start")
                        }
                    )
                }

                composable("gameover") {
                    GameOverScreen(GameState.score) {
                        GameState.reset()
                        navController.navigate("start")
                    }
                }
            }

            if (currentRoute != "start") {
                IconButton(
                    onClick = { showConfirmQuit = true },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Quitter",
                        tint = MaterialTheme.colors.onSurface
                    )
                }
            }

            if (showConfirmQuit) {
                AlertDialog(
                    onDismissRequest = { showConfirmQuit = false },
                    title = { Text("Quitter") },
                    text = { Text("Voulez-vous vraiment quitter et revenir au menu d'accueil ?") },
                    confirmButton = {
                        TextButton(onClick = {
                            showConfirmQuit = false
                            GameState.reset()
                            navController.navigate("start")
                        }) {
                            Text("Oui")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showConfirmQuit = false }) {
                            Text("Non")
                        }
                    }
                )
            }
        }
    }
}
