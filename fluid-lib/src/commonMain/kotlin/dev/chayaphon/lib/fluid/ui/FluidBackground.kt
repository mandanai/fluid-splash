package dev.chayaphon.lib.fluid.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import dev.chayaphon.lib.fluid.model.Background
import dev.chayaphon.lib.fluid.model.FluidAnimationValues
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun FluidBackground(
    background: Background,
    animationValues: FluidAnimationValues,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .graphicsLayer {
                this.scaleX = animationValues.scaleX
                this.scaleY = animationValues.scaleY
                translationY = animationValues.offsetY
            }
    ) {
        when (background) {
            is Background.BackgroundPainter -> {
                Image(
                    painter = background.painter,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            is Background.BackgroundColor -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(background.color)
                )
            }
        }
    }
}
