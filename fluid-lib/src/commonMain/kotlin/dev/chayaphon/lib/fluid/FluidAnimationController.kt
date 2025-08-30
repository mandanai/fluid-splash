package dev.chayaphon.lib.fluid

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import dev.chayaphon.lib.fluid.model.FluidParticle
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * Fluid animation controller for managing particle systems
 */
class FluidAnimationController {
    private val particles = mutableListOf<FluidParticle>()
    private var time = 0f

    var version by mutableStateOf(0)

    /**
     * Update all particles in the system
     */
    fun update(deltaTime: Float, bounds: Size) {
        time += deltaTime

        particles.forEach { particle ->
            val upwardForce = -1.0f
            val wobble = sin(time * 2f + particle.position.x / 10f) * 0.3f
            particle.velocity = Offset(
                (particle.velocity.x * 0.99f) + wobble,
                (particle.velocity.y * 0.99f) + upwardForce
            )
            particle.position = Offset(
                particle.position.x + particle.velocity.x * deltaTime,
                particle.position.y + particle.velocity.y * deltaTime
            )
            particle.life -= deltaTime * 0.25f
        }

        // Remove particles that are dead (faded out) or have floated off the top of the screen.
        particles.removeAll { it.life <= 0 || it.position.y + it.radius < 0 }

        version++
    }

    /**
     * Spawn a burst of particles at given position
     */
    fun burst(center: Offset, count: Int, color: Color) {
        repeat(count) {
            val angle = Random.nextFloat() * 2 * PI.toFloat()
            val speed = Random.nextFloat() * 5 + 2 // Reduced speed for a calmer effect

            particles.add(
                FluidParticle(
                    position = center,
                    velocity = Offset(
                        cos(angle) * speed,
                        // Give a strong initial upward push
                        sin(angle) * speed - (Random.nextFloat() * 20 + 12)
                    ),
                    radius = Random.nextFloat() * 20 + 8, // Bubbles are a bit smaller
                    color = color.copy(alpha = 0.3f)
                )
            )
        }
    }

    /**
     * Draw all particles
     */
    fun draw(drawScope: DrawScope) {
        particles.forEach { particle ->
            drawScope.drawCircle(
                color = particle.color.copy(alpha = particle.life.coerceIn(0f, 1f)),
                radius = particle.radius,
                center = particle.position
            )
        }
    }
}

// Extension functions for easier color manipulation
fun Color.lighter(factor: Float = 0.2f): Color {
    return copy(
        red = (red + (1 - red) * factor).coerceIn(0f, 1f),
        green = (green + (1 - green) * factor).coerceIn(0f, 1f),
        blue = (blue + (1 - blue) * factor).coerceIn(0f, 1f)
    )
}

fun Color.darker(factor: Float = 0.2f): Color {
    return copy(
        red = (red * (1 - factor)).coerceIn(0f, 1f),
        green = (green * (1 - factor)).coerceIn(0f, 1f),
        blue = (blue * (1 - factor)).coerceIn(0f, 1f)
    )
}

// Random number generator helper
object Random {
    fun nextFloat(): Float = kotlin.random.Random.nextFloat()
    fun nextInt(until: Int): Int = kotlin.random.Random.nextInt(until)
}