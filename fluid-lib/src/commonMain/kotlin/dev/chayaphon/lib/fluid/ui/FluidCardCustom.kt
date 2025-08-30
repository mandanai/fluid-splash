package dev.chayaphon.lib.fluid.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
        // Content overlay
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 75.dp, bottom = 25.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}