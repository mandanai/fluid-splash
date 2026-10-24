package dev.chayaphon.fluid.fluidsplash.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.chayaphon.fluid.fluidsplash.di.AppContainer
import dev.chayaphon.fluid.fluidsplash.presentation.cool.CoolScreen
import dev.chayaphon.fluid.fluidsplash.presentation.introduce.IntroduceRoute
import dev.chayaphon.fluid.fluidsplash.presentation.introduce.IntroduceViewModel
import dev.chayaphon.fluid.fluidsplash.presentation.loading.LoadingRoute
import dev.chayaphon.fluid.fluidsplash.presentation.loading.LoadingViewModel
import dev.chayaphon.fluid.fluidsplash.presentation.metaball.MetaballScreen
import dev.chayaphon.fluid.fluidsplash.presentation.start.StartScreen

@Composable
fun AppNavHost(
    appContainer: AppContainer,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Route.Start,
        modifier = modifier,
    ) {
        composable<Route.Start> {
            StartScreen(onDemoClick = { route -> navController.navigate(route) })
        }
        composable<Route.Introduce> {
            IntroduceRoute(
                viewModel = viewModel { IntroduceViewModel(appContainer.getOnboardingPagesUseCase) }
            )
        }
        composable<Route.Cool> {
            CoolScreen()
        }
        composable<Route.Loading> {
            LoadingRoute(viewModel = viewModel { LoadingViewModel() })
        }
        composable<Route.Metaball> {
            MetaballScreen()
        }
    }
}
