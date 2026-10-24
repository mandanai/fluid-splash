package dev.chayaphon.fluid.fluidsplash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import dev.chayaphon.fluid.fluidsplash.di.AppContainer
import dev.chayaphon.fluid.fluidsplash.presentation.navigation.AppNavHost
import dev.chayaphon.fluid.fluidsplash.presentation.theme.FluidSplashTheme

@Composable
@Preview
fun App() {
    val appContainer = remember { AppContainer() }

    FluidSplashTheme {
        AppNavHost(appContainer = appContainer)
    }
}
