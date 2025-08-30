package dev.chayaphon.lib.fluid.ui

import androidx.compose.runtime.compositionLocalOf

/**
 * Provides carousel context information (current index and total pages)
 * to children Composable like FluidCard.
 */
data class CarouselInfo(
    val currentIndex: Int,
    val allPage: Int
)

val LocalCarouselInfo = compositionLocalOf<CarouselInfo?> { null }
