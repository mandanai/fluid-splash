package dev.chayaphon.fluid.fluidsplash.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

sealed class Screen(val route: String) {
    object Start : Screen("start")
    object Cool : Screen("cool")
    object Loading : Screen("loading")
    object Metaball : Screen("metaball")
    object Showcase : Screen("showcase")
}

@Composable
fun StartPage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 35.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate(Screen.Showcase.route) }
        ) {
            Text("Introduce Screen")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate(Screen.Cool.route) }
        ) {
            Text("Cool Screen")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate(Screen.Loading.route) }
        ) {
            Text("Loading Screen")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate(Screen.Metaball.route) }
        ) {
            Text("Metaball Screen")
        }
    }
}
