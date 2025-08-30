package dev.chayaphon.lib.fluid.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * Liquid fill animation with surface tension effect
 */
@Composable
fun LiquidFillAnimation(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = Color(0xFF6750A4),
    waveHeight: Float = 30f
) {
    val infiniteTransition = rememberInfiniteTransition(label = "liquid")

    val waveOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2 * PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "waveOffset"
    )

    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        val fillHeight = height * (1 - progress)

        // Create liquid path with surface tension effect
        val liquidPath = Path().apply {
            moveTo(0f, height)
            lineTo(0f, fillHeight)

            // Add wave with surface tension
            for (x in 0..width.toInt() step 2) {
                val normalizedX = x / width
                val wave1 = sin(normalizedX * 4 * PI + waveOffset) * waveHeight
                val wave2 = sin(normalizedX * 8 * PI - waveOffset * 2) * waveHeight * 0.3f
                val surfaceTension = cos(normalizedX * PI) * waveHeight * 0.2f

                val y = fillHeight + wave1 + wave2 + surfaceTension
                lineTo(x.toFloat(), y.toFloat())
            }

            lineTo(width, height)
            close()
        }

        // Draw liquid with gradient
        drawPath(
            path = liquidPath,
            brush = Brush.verticalGradient(
                colors = listOf(
                    color.copy(alpha = 0.8f),
                    color
                ),
                startY = fillHeight,
                endY = height
            )
        )

        // Add highlight on the surface
        drawPath(
            path = liquidPath,
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color.White.copy(alpha = 0.3f),
                    Color.Transparent
                ),
                startY = fillHeight,
                endY = fillHeight + waveHeight * 2
            )
        )
    }
}