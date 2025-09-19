package dev.chayaphon.lib.fluid.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.chayaphon.lib.fluid.model.Background
import org.jetbrains.compose.resources.ExperimentalResourceApi
import kotlin.time.ExperimentalTime

/**
 * FluidCard - Card with an animated fluid background
 */
@OptIn(ExperimentalResourceApi::class, ExperimentalTime::class)
@Composable
fun FluidCardCustom(
    modifier: Modifier = Modifier,
    background: Background,
    content: @Composable () -> Unit
) {
    FluidContainer(
        background = background,
        modifier = modifier
    ) {
        content()
    }
}