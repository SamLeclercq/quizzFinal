package com.worldline.quiz.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.worldline.quiz.core.GameState
import kotlin.random.Random

@Composable
fun StartScreen(onStartGame: () -> Unit) {
    val starCount = 60
    val starPositions = remember {
        List(starCount) { Pair(Random.nextFloat(), Random.nextFloat()) }
    }

    var selectedTheme by remember { mutableStateOf(GameState.selectedTheme) } 
    val themes = listOf("Star Wars", "Général", "Géographie")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF222831), Color(0xFF393E46))
                )
            )
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height
            starPositions.forEach { (sx, sy) ->
                drawCircle(
                    color = Color.White.copy(alpha = 0.5f),
                    center = Offset(sx * w, sy * h),
                    radius = 1.7f
                )
            }
        }

        Text(
            text = "🚀",
            fontSize = 80.sp,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 150.dp)
        )

        Card(
            backgroundColor = Color(0xFF1E1E1E),
            shape = RoundedCornerShape(12.dp),
            elevation = 6.dp,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 32.dp)
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Règles du jeu",
                    fontSize = 18.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    color = Color.Yellow
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "• Répondez correctement aux questions.\n" +
                            "• Chaque étape a une difficulté assignée.\n" +
                            "• Augmentez votre streak pour débloquer des jokers.\n" +
                            "• Un échec termine la partie.",
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 180.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Choisissez un thème :",
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                themes.forEach { theme ->
                    Button(
                        onClick = {
                            selectedTheme = theme
                            GameState.setTheme(theme) 
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (selectedTheme == theme) Color(0xFF1A73E8) else Color(0xFF333333)
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.padding(horizontal = 4.dp)
                    ) {
                        Text(theme, color = Color.White)
                    }
                }
            }
        }

        Button(
            onClick = {
                GameState.setTheme(selectedTheme) 
                onStartGame()
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp)
                .fillMaxWidth(0.7f),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1E1E1E)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Commencer",
                fontSize = 20.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
