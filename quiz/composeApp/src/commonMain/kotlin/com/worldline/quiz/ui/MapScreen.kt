package com.worldline.quiz.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.worldline.quiz.core.GameState
import com.worldline.quiz.core.NodeState
import kotlin.math.*
import kotlin.random.Random
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun MapScreen(
    nodeStates: List<NodeState>,
    onNodeClicked: (Int) -> Unit,
    onBossClicked: () -> Unit
) {
    val positions = naturalNodePositions()
    val edges = naturalEdges()

    val starCount = 60
    val starPositions = remember {
        List(starCount) { Pair(Random.nextFloat(), Random.nextFloat()) }
    }

    val nebulaCount = 4
    val nebulaData = remember {
        List(nebulaCount) {
            val rx = Random.nextFloat()
            val ry = Random.nextFloat()
            val radius = 100f + Random.nextFloat() * 150f
            Triple(rx, ry, radius)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF222831), Color(0xFF393E46))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(15.dp)
        ) {
            Text(
                text = "🚀 ${GameState.streakCount}",
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "🎭 ${GameState.collectedJokers.size}",
                fontSize = 20.sp,
                color = Color.White

            )
        }

        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            nebulaData.forEach { (rx, ry, rad) ->
                drawCircle(
                    color = randomNebulaColor().copy(alpha = 0.2f),
                    center = Offset(rx * w, ry * h),
                    radius = rad
                )
            }
            starPositions.forEach { (sx, sy) ->
                drawCircle(
                    color = Color.White.copy(alpha = 0.5f),
                    center = Offset(sx * w, sy * h),
                    radius = 1.7f
                )
            }
        }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(nodeStates) {
                    detectTapGestures { tapOffset ->
                        val currentNode = nodeStates.lastIndexOf(NodeState.SELECTED)

                        if (currentNode == -1) {
                            for (nodeId in nodeStates.indices) {
                                if (nodeStates[nodeId] == NodeState.AVAILABLE) {
                                    val (x, y) = positions[nodeId]
                                    val center = Offset(x * size.width, y * size.height)
                                    val dist = hypot(tapOffset.x - center.x, tapOffset.y - center.y)
                                    if (dist <= 50f) {
                                        if (nodeId == 14) onBossClicked() else onNodeClicked(nodeId)
                                        return@detectTapGestures
                                    }
                                }
                            }
                        } else {
                            val connectedNodes = edges
                                .filter { it.first == currentNode }
                                .map { it.second }
                                .plus(edges.filter { it.second == currentNode }.map { it.first })

                            for (nodeId in connectedNodes) {
                                if (nodeStates[nodeId] == NodeState.AVAILABLE) {
                                    val (x, y) = positions[nodeId]
                                    val center = Offset(x * size.width, y * size.height)
                                    val dist = hypot(tapOffset.x - center.x, tapOffset.y - center.y)
                                    if (dist <= 50f) {
                                        if (nodeId == 14) onBossClicked() else onNodeClicked(nodeId)
                                        return@detectTapGestures
                                    }
                                }
                            }
                        }
                    }
                }
        ) {
            val w = size.width
            val h = size.height

            edges.forEach { (start, end) ->
                val (sx, sy) = positions[start]
                val (ex, ey) = positions[end]
                val startOffset = Offset(sx * w, sy * h)
                val endOffset = Offset(ex * w, ey * h)

                val isBothSelected = (
                        nodeStates[start] == NodeState.SELECTED &&
                                nodeStates[end] == NodeState.SELECTED
                        )
                val lineColor = if (isBothSelected) Color(0xFF4CAF50) else Color(0xFFAAAAAA)
                val lineAlpha = if (isBothSelected) 1f else 0.5f

                val path = Path().apply {
                    moveTo(startOffset.x, startOffset.y)
                    val midX = (startOffset.x + endOffset.x) / 2
                    val midY = (startOffset.y + endOffset.y) / 2 - 30f
                    quadraticBezierTo(midX, midY, endOffset.x, endOffset.y)
                }
                drawPath(
                    path = path,
                    color = lineColor.copy(alpha = lineAlpha),
                    style = Stroke(
                        width = 3f,
                        pathEffect = if (isBothSelected) null
                        else PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                    )
                )
            }

            for (nodeId in nodeStates.indices) {
                val (x, y) = positions[nodeId]
                val nodeState = nodeStates[nodeId]
                val isBoss = (nodeId == 14)
                val center = Offset(x * w, y * h)

                if (isBoss) {
                    val bossColor = when (nodeState) {
                        NodeState.LOCKED    -> Color.Red.copy(alpha = 0.5f)
                        NodeState.AVAILABLE -> Color.Red
                        NodeState.SELECTED  -> Color(0xFFFF5252)
                        NodeState.SKIPPED   -> Color.DarkGray
                    }
                    drawStar(
                        center = center,
                        outerRadius = 25f,
                        innerRadius = 12f,
                        points = 5,
                        color = bossColor
                    )
                    drawStar(
                        center = center,
                        outerRadius = 25f,
                        innerRadius = 12f,
                        points = 5,
                        color = bossColor.copy(alpha = 0.3f),
                        style = Stroke(width = 4f)
                    )
                } else {
                    val (color1, color2) = colorsForNode(nodeId, nodeState)

                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = listOf(color1, color2),
                            center = center,
                            radius = 20f
                        ),
                        center = center,
                        radius = 20f
                    )
                    drawCircle(
                        color = color1,
                        center = center,
                        radius = 20f,
                        style = Stroke(width = 3f)
                    )
                }
            }
        }
    }
}

fun colorsForNode(nodeId: Int, state: NodeState): Pair<Color, Color> {
    val difficulty = GameState.nodeDifficulties[nodeId]

    val (base1, base2) = when (difficulty) {
        "Facile" -> Color(0xFFA5D6A7) to Color(0xFF66BB6A)  
        "Moyenne" -> Color(0xFFFFF59D) to Color(0xFFFFEE58) 
        else -> Color(0xFFFFCDD2) to Color(0xFFFF5252)     
    }

    return when (state) {
        NodeState.LOCKED -> base1.copy(alpha = 0.3f) to base2.copy(alpha = 0.3f)
        NodeState.SKIPPED -> Color.DarkGray to Color.Black
        NodeState.AVAILABLE -> base1 to base2
        NodeState.SELECTED -> base1.darken(0.2f) to base2.darken(0.2f)
    }
}

fun Color.darken(factor: Float): Color {
    return Color(
        red = (red * (1f - factor)).coerceIn(0f, 1f),
        green = (green * (1f - factor)).coerceIn(0f, 1f),
        blue = (blue * (1f - factor)).coerceIn(0f, 1f),
        alpha = alpha
    )
}


fun DrawScope.drawStar(
    center: Offset,
    outerRadius: Float,
    innerRadius: Float,
    points: Int,
    color: Color,
    style: DrawStyle = Fill
) {
    val path = Path()
    val angleStep = (2 * PI / points)
    for (i in 0 until points * 2) {
        val r = if (i % 2 == 0) outerRadius else innerRadius
        val angle = i * angleStep / 2
        val x = center.x + r * cos(angle).toFloat()
        val y = center.y + r * sin(angle).toFloat()
        if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
    }
    path.close()
    drawPath(path, color, style = style)
}

fun randomNebulaColor(): Color {
    val hue = Random.nextFloat() * 360f
    val saturation = 0.4f
    val brightness = 1f
    return Color.hsv(hue, saturation, brightness)
}

fun naturalNodePositions(): List<Pair<Float, Float>> = listOf(
    0.50f to 0.95f, // node 0
    0.15f to 0.88f, // node 1
    0.85f to 0.88f, // node 2
    0.10f to 0.80f, // node 3
    0.50f to 0.80f, // node 4
    0.90f to 0.80f, // node 5
    0.1f  to 0.65f, // node 6
    0.55f to 0.67f, // node 7
    0.87f to 0.65f, // node 8
    0.15f to 0.50f, // node 9
    0.50f to 0.40f, // node 10
    0.83f to 0.50f, // node 11
    0.30f to 0.25f, // node 12
    0.70f to 0.30f, // node 13
    0.50f to 0.10f  // node 14 (Boss)
)

fun naturalEdges(): List<Pair<Int, Int>> = listOf(
    0 to 1,
    0 to 2,
    1 to 3,
    1 to 4,
    2 to 5,
    4 to 7,
    4 to 8,
    3 to 6,
    5 to 7,
    6 to 9,
    7 to 10,
    7 to 9,
    8 to 11,
    9 to 12,
    10 to 13,
    11 to 13,
    12 to 14,
    13 to 14
)
