package dev.chayaphon.lib.fluid.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.chayaphon.lib.fluid.model.Background
import dev.chayaphon.lib.fluid.utils.calculateFluidAnimationValues
import dev.chayaphon.lib.fluid.utils.rememberFluidAnimationTime

@Composable
fun FluidContainer(
    background: Background,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val time = rememberFluidAnimationTime()
    val animationValues = remember(time) { calculateFluidAnimationValues(time) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .background(Color.Black)
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        FluidBackground(
            background = background,
            animationValues = animationValues
        )

        content()
    }
}