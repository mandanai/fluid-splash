package dev.chayaphon.lib.fluid.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import kotlin.math.PI
import kotlin.math.sin

/**
 * Composable for an animated fluid background
 */
@Composable
fun AnimatedFluidBackground(
    modifier: Modifier = Modifier,
    primaryColor: Color = Color(0xFF6750A4),
    secondaryColor: Color = Color(0xFF8B7BAE)
) {
    val infiniteTransition = rememberInfiniteTransition(label = "fluid")
    val wavePhase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2 * PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "wavePhase"
    )

    val amplitude by infiniteTransition.animateFloat(
        initialValue = 20f,
        targetValue = 40f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "amplitude"
    )

    Canvas(modifier = modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height

        drawRect(
            brush = Brush.verticalGradient(
                colors = listOf(
                    secondaryColor.copy(alpha = 0.3f),
                    primaryColor.copy(alpha = 0.5f)
                )
            )
        )

        for (layer in 0..2) {
            val layerOffset = layer * 50f
            val layerAmplitude = amplitude * (1 - layer * 0.2f)

            val wavePath = Path().apply {
                moveTo(0f, height)
                lineTo(0f, height * 0.6f + layerOffset)

                for (x in 0..width.toInt() step 5) {
                    val y = height * 0.6f + layerOffset +
                            sin(x * 0.01f + wavePhase * (layer + 1)) * layerAmplitude + // Speeds are now 1, 2, 3
                            sin(x * 0.02f - wavePhase) * layerAmplitude * 0.5f           // Speed is now -1
                    lineTo(x.toFloat(), y)
                }

                lineTo(width, height)
                close()
            }

            drawPath(
                path = wavePath,
                color = primaryColor.copy(alpha = 0.3f - layer * 0.1f)
            )
        }
    }
}