package dev.chayaphon.lib.fluid.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

sealed interface Background {
    data class BackgroundPainter(val painter: Painter) : Background
    data class BackgroundColor(val color: Color) : Background
}