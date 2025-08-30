package dev.chayaphon.lib.fluid.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

/**
 * Data class representing a fluid particle
 */
data class FluidParticle(
    var position: Offset,
    var velocity: Offset,
    var radius: Float,
    var color: Color,
    var life: Float = 1f
)