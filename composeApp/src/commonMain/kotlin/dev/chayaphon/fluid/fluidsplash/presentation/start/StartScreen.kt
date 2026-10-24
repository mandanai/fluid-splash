package dev.chayaphon.fluid.fluidsplash.presentation.start

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
import dev.chayaphon.fluid.fluidsplash.presentation.navigation.Route

private class Demo(val label: String, val route: Route)

private val Demos = listOf(
    Demo("Introduce Screen", Route.Introduce),
    Demo("Cool Screen", Route.Cool),
    Demo("Loading Screen", Route.Loading),
    Demo("Metaball Screen", Route.Metaball),
)

@Composable
fun StartScreen(
    onDemoClick: (Route) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 35.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
    ) {
        Demos.forEach { demo ->
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onDemoClick(demo.route) }
            ) {
                Text(demo.label)
            }
        }
    }
}
