package dev.chayaphon.fluid.fluidsplash

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.chayaphon.fluid.fluidsplash.presentation.CoolScreen
import dev.chayaphon.fluid.fluidsplash.presentation.LoadingScreen
import dev.chayaphon.fluid.fluidsplash.presentation.MetaballScreen
import dev.chayaphon.fluid.fluidsplash.presentation.Screen
import dev.chayaphon.fluid.fluidsplash.presentation.IntroduceScreen
import dev.chayaphon.fluid.fluidsplash.presentation.StartPage
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    FluidSplashTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Screen.Start.route) {
            composable(Screen.Start.route) {
                StartPage(navController)
            }
            composable(Screen.Cool.route) {
                CoolScreen()
            }
            composable(Screen.Loading.route) {
                LoadingScreen()
            }
            composable(Screen.Metaball.route) {
                MetaballScreen()
            }
            composable(Screen.Showcase.route) {
                IntroduceScreen()
            }
        }
    }
}

object FluidSplashTheme {
    @Composable
    operator fun invoke(content: @Composable () -> Unit) {
        MaterialTheme(
            colorScheme = lightColorScheme(
                primary = Color(0xFF6750A4),
                secondary = Color(0xFF625B71),
                background = Color.White,
                surface = Color.White,
            ),
            content = content
        )
    }
}