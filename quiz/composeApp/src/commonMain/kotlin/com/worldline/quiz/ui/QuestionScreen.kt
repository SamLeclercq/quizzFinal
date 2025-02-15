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
import kotlin.math.hypot
import kotlin.random.Random

fun miniBossEmojiForDifficulty(difficulty: String): String {
    return when (difficulty) {
        "Facile" -> "🦄"
        "Moyenne" -> "👾"
        else -> "🐉"
    }
}

@Composable
fun QuestionScreen(
    nodeId: Int,

    onAnswerCorrect: () -> Unit,
    onAnswerWrong: () -> Unit
) {
    var question by remember { mutableStateOf(GameState.getQuestionForNode(nodeId)) }
    var selectedAnswer by remember { mutableStateOf<Int?>(null) }
    var showJokerDialog by remember { mutableStateOf(false) }
    var selectedJoker by remember { mutableStateOf<JokerType?>(null) }
    var miniBossHp by remember { mutableStateOf(1) }

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
        Text(
            text = "🚀 ${GameState.streakCount}",
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp)
        )
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(top = 120.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val currentDifficulty = GameState.nodeDifficulties[nodeId]
            Text(
                text = miniBossEmojiForDifficulty(currentDifficulty),
                fontSize = 64.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            LinearProgressIndicator(
                progress = miniBossHp / 1f,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(8.dp),
                color = Color.Red,
                backgroundColor = Color.DarkGray
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 250.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (GameState.collectedJokers.isNotEmpty()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    GameState.collectedJokers.forEach { joker ->
                        Text(
                            text = JokerManager.getJokerEmoji(joker),
                            fontSize = 40.sp, 
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

            Text(
                text = question.text,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            question.answers.forEach { answer ->
                if (!answer.hidden) {
                    Button(
                        onClick = { selectedAnswer = answer.id },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (selectedAnswer == answer.id) Color(0xFF1A73E8) else Color(0xFF333333)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = answer.text,
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Normal
                        )
                    }
                }
            }
            Button(
                onClick = {
                    if (selectedAnswer == question.correctAnswerId) {
                        miniBossHp = 0
                        onAnswerCorrect()
                    } else if (GameState.allowDoubleTap) {
                        GameState.allowDoubleTap = false
                    } else {
                        onAnswerWrong()
                    }
                },
                enabled = (selectedAnswer != null),
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(top = 8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Valider",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )
            }
        }
    }

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
                        JokerManager.useJoker(selectedJoker!!, question, { question = it }, onAnswerCorrect)
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
