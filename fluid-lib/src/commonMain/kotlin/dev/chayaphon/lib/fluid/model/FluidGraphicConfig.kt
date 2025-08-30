package dev.chayaphon.lib.fluid.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

data class FluidGraphicConfig(
    val background: Background,
    val illustration: Painter? = null,
    val illustrationPlaceholder: String = "✨",
    val slider: Painter? = null,
    val indicatorColor: Color = Color.White
)
