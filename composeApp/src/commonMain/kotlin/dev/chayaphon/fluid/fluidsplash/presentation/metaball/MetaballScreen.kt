package dev.chayaphon.fluid.fluidsplash.presentation.metaball

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import dev.chayaphon.lib.fluid.ui.MetaballBackground

@Composable
fun MetaballScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        contentAlignment = Alignment.Center
    ) {
        MetaballBackground()

        Text(
            text = "Metaballs!",
            color = Color.White,
            fontSize = 40.sp
        )
    }
}
