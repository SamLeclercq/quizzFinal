package com.worldline.quiz.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.worldline.quiz.core.GameState
import com.worldline.quiz.data.JokerManager
import com.worldline.quiz.data.JokerType
import com.worldline.quiz.data.Question
import org.jetbrains.compose.resources.ThemeQualifier
import kotlin.math.hypot
import kotlin.random.Random

@Composable
fun BossScreen(
    onBossDefeated: () -> Unit,
    onGameOver: () -> Unit
) {
    var bossQuestions by remember { mutableStateOf(GameState.getBossQuestions()) }
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var bossQuestion by remember { mutableStateOf(bossQuestions[currentQuestionIndex]) }
    var selectedAnswer by remember { mutableStateOf<Int?>(null) }
    var showJokerDialog by remember { mutableStateOf(false) }
    var selectedJoker by remember { mutableStateOf<JokerType?>(null) }
    // Boss HP sur 3 points
    var bossHp by remember { mutableStateOf(3) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF222831), Color(0xFF393E46))
                )
            )
            .padding(16.dp)
    ) {
        // Compteur de streak en haut à gauche
        Text(
            text = "🚀 ${GameState.streakCount}",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp)
        )

        // Starfield en fond
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height
            repeat(60) {
                val x = Random.nextFloat() * w
                val y = Random.nextFloat() * h
                drawCircle(
                    color = Color.White.copy(alpha = 0.3f),
                    center = Offset(x, y),
                    radius = 1.5f
                )
            }
        }

        // Disposition principale dans une Column centrée verticalement
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Mini-boss simulation
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Emoji fixe pour le boss (ici "👹")
                Text(
                    text = "👹",
                    fontSize = 64.sp,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                // Barre d'HP du boss sur 3 points
                LinearProgressIndicator(
                    progress = bossHp / 3f,
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(8.dp),
                    color = Color.Red,
                    backgroundColor = Color.DarkGray
                )
            }


            // Section question et réponses (remontée)// Disponibilité des jokers, placés juste en dessous de la simulation du boss
            //            if (GameState.collectedJokers.isNotEmpty()) {
            //                Row(
            //                    modifier = Modifier.fillMaxWidth(),
            //                    horizontalArrangement = Arrangement.Center
            //                ) {
            //                    GameState.collectedJokers.forEach { joker ->
            //                        Text(
            //                            text = JokerManager.getJokerEmoji(joker),
            //                            fontSize = 40.sp, // Taille un peu plus petite que l'emoji du boss
            //                            modifier = Modifier
            //                                .padding(horizontal = 8.dp)
            //                                .clickable {
            //                                    selectedJoker = joker
            //                                    showJokerDialog = true
            //                                },
            //                            color = Color.White
            //                        )
            //                    }
            //                }
            //            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp), // Moins de padding pour remonter cette section
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (GameState.collectedJokers.isNotEmpty()) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        GameState.collectedJokers.forEach { joker ->
                                            Text(
                                                text = JokerManager.getJokerEmoji(joker),
                                                fontSize = 40.sp, // Taille un peu plus petite que l'emoji du boss
                                                modifier = Modifier
                                                    .padding(horizontal = 8.dp)
                                                    .clickable {
                                                        selectedJoker = joker
                                                        showJokerDialog = true
                                                    },
                                                color = Color.White
                                            )
                                        }
                                    }
                                }
                Spacer(modifier = Modifier.height(15.dp))
                // Question du boss
                Text(
                    text = bossQuestion.text,
                    fontSize = 22.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                // Boutons de réponse
                bossQuestion.answers.forEach { answer ->
                    if (!answer.hidden) {
                        Button(
                            onClick = { selectedAnswer = answer.id },
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                                .padding(vertical = 4.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = if (selectedAnswer == answer.id) Color(0xFF1A73E8) else Color(0xFF333333)
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = answer.text,
                                fontSize = 16.sp,
                                fontWeight = androidx.compose.ui.text.font.FontWeight.Normal,
                                color = Color.White
                            )
                        }
                    }
                }
                // Bouton Valider
                Button(
                    onClick = {
                        if (selectedAnswer == bossQuestion.correctAnswerId) {
                            bossHp--
                            if (bossHp <= 0) {
                                GameState.streakCount++
                                GameState.unlockRandomJoker()
                                onBossDefeated()
                            } else {
                                if (currentQuestionIndex < bossQuestions.size - 1) {
                                    currentQuestionIndex++
                                    bossQuestion = bossQuestions[currentQuestionIndex]
                                    selectedAnswer = null
                                } else {
                                    GameState.streakCount++
                                    GameState.unlockRandomJoker()
                                    onBossDefeated()
                                }
                            }
                        } else if (GameState.allowDoubleTap) {
                            GameState.allowDoubleTap = false
                        } else {
                            onGameOver()
                        }
                    },
                    enabled = (selectedAnswer != null),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(top = 8.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Valider",
                        fontSize = 18.sp,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }

    // Fenêtre de dialogue pour confirmer l'utilisation d'un Joker
    if (showJokerDialog && selectedJoker != null) {
        AlertDialog(
            onDismissRequest = { showJokerDialog = false },
            title = { Text("Utiliser un Joker ?", fontSize = 20.sp, color = Color.White) },
            text = {
                Text(
                    text = "Effet : ${JokerManager.getJokerEffect(selectedJoker!!)}",
                    fontSize = 14.sp,
                    color = Color.White
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        JokerManager.useJoker(selectedJoker!!, bossQuestion, { bossQuestion = it }, onBossDefeated)
                        showJokerDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1E1E1E)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Confirmer", color = Color.White, fontSize = 14.sp)
                }
            },
            dismissButton = {
                Button(
                    onClick = { showJokerDialog = false },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Annuler", color = Color.White, fontSize = 14.sp)
                }
            },
            backgroundColor = Color(0xFF424242),
            contentColor = Color.White
        )
    }
}
