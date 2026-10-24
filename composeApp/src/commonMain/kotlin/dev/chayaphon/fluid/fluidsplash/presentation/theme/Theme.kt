package dev.chayaphon.fluid.fluidsplash.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val FluidSplashColorScheme = lightColorScheme(
    primary = Color(0xFF6750A4),
    secondary = Color(0xFF625B71),
    background = Color.White,
    surface = Color.White,
)

@Composable
fun FluidSplashTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = FluidSplashColorScheme,
        content = content
    )
}
