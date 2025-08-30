package dev.chayaphon.lib.fluid.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import dev.chayaphon.lib.fluid.Random

/**
 * Metaball effect for fluid simulation
 */
class MetaballRenderer(
    private val ballCount: Int = 5,
    private val ballRadius: Float = 100f,
    private val colors: List<Color> = listOf(
        Color(0xFF673AB7),
        Color(0xFF3F51B5),
        Color(0xFF4FC3F7),
        Color(0xFF26A69A)
    )
) {
    data class Metaball(
        var x: Float,
        var y: Float,
        var vx: Float,
        var vy: Float,
        val radius: Float,
        val color: Color
    )

    // Add this state variable to trigger UI redraws.
    var version by mutableStateOf(0)

    private val metaballs = List(ballCount) {
        Metaball(
            x = Random.nextFloat() * 500,
            y = Random.nextFloat() * 500,
            vx = Random.nextFloat() * 4 - 2,
            vy = Random.nextFloat() * 4 - 2,
            radius = ballRadius * (0.5f + Random.nextFloat() * 0.5f),
            color = colors.random()
        )
    }

    /**
     * Update metaball positions
     */
    fun update(bounds: Size) {
        if (bounds.width == 0f || bounds.height == 0f) return

        metaballs.forEach { ball ->
            ball.x += ball.vx
            ball.y += ball.vy

            // Bounce off walls using the correct screen bounds.
            if (ball.x < ball.radius || ball.x > bounds.width - ball.radius) {
                ball.vx = -ball.vx
                ball.x = ball.x.coerceIn(ball.radius, bounds.width - ball.radius)
            }
            if (ball.y < ball.radius || ball.y > bounds.height - ball.radius) {
                ball.vy = -ball.vy
                ball.y = ball.y.coerceIn(ball.radius, bounds.height - ball.radius)
            }
        }

        version++
    }

    /**
     * Draw metaballs with threshold rendering
     */
    fun draw(drawScope: DrawScope, threshold: Float = 1f) {
        with(drawScope) {
            // Simple approach: draw blurred circles that blend together
            metaballs.forEach { ball ->
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            ball.color,
                            ball.color.copy(alpha = 0.5f),
                            ball.color.copy(alpha = 0f)
                        ),
                        center = Offset(ball.x, ball.y),
                        radius = ball.radius * 1.5f
                    ),
                    radius = ball.radius * 1.5f,
                    center = Offset(ball.x, ball.y),
                    blendMode = BlendMode.SrcOver
                )
            }
        }
    }
}