package dev.chayaphon.lib.fluid.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.chayaphon.lib.fluid.model.FluidAnimationValues
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.math.cos
import kotlin.math.sin
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun rememberFluidAnimationTime(): Float {
    var time by remember { mutableStateOf(0f) }

    LaunchedEffect(Unit) {
        while (isActive) {
            time = Clock.System.now().toEpochMilliseconds() / 2000f
            delay(16) // 60 FPS=
        }
    }

    return time
}

fun calculateFluidAnimationValues(time: Float): FluidAnimationValues {
    return FluidAnimationValues(
        scaleX = 1.2f + sin(time) * 0.05f,
        scaleY = 1.2f + cos(time) * 0.07f,
        offsetY = 20f + cos(time) * 20f
    )
}