package dev.chayaphon.fluid.fluidsplash.model

import androidx.compose.ui.graphics.Color
import dev.chayaphon.lib.fluid.model.FluidGraphicConfig

/**
 * Data class for card information
 */
data class CardData(
    val config: FluidGraphicConfig,
    val altColor: Color,
    val title: String,
    val subtitle: String
)