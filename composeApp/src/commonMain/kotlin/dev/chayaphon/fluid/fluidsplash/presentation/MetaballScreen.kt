package dev.chayaphon.fluid.fluidsplash.presentation

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
fun MetaballScreen() {
    val mySunsetPalette = listOf(
        Color(0xfff7b267),
        Color(0xfff79d65),
        Color(0xfff4845f),
        Color(0xfff27059),
        Color(0xfff25c54)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        contentAlignment = Alignment.Center
    ) {
        // Call the composable to display the metaball animation.
        MetaballBackground()

        // Other content on top.
        Text(
            text = "Metaballs!",
            color = Color.White,
            fontSize = 40.sp
        )
    }
}