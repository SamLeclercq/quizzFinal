package com.worldline.quiz.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.worldline.quiz.core.GameState
import com.worldline.quiz.data.JokerManager
import kotlin.random.Random

@Composable
fun VictoryScreen(
    onContinue: () -> Unit,
    onQuit: () -> Unit
) {
    val newJoker = remember { GameState.lastUnlockedJoker }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF222831), Color(0xFF393E46))
                )
            )
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(15.dp)
        ) {
            Text(
                text = "🚀 ${GameState.streakCount}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "🎭 ${GameState.collectedJokers.size}",
                fontSize = 22.sp, 
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }

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
                .padding(top = 180.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Félicitations !",
                fontSize = 28.sp, 
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFFFFD700)
            )
            Spacer(modifier = Modifier.height(80.dp)) 
            if (newJoker != null) {
                Card(
                    backgroundColor = Color(0xFF222222),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(2.dp, Color(0xFFFFD700)),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Nouveau Joker débloqué !",
                            fontSize = 20.sp,
                            color = Color(0xFFFFD700),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = JokerManager.getJokerEmoji(newJoker),
                            fontSize = 40.sp,
                            color = Color.White
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Button(
                onClick = onContinue,
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1E88E5)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Continuer la Streak", fontSize = 18.sp, color = Color.White)
            }

            Button(
                onClick = {
                    GameState.reset()
                    onQuit()
                },
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF424242)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("S'arrêter ici", fontSize = 18.sp, color = Color.White)
            }
        }
    }
}
