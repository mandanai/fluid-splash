package dev.chayaphon.fluid.fluidsplash.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import dev.chayaphon.lib.fluid.ui.AnimatedFluidBackground

@Composable
fun CoolScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // 1. Call AnimatedFluidBackground here to set it as the background.
        AnimatedFluidBackground(
            modifier = Modifier.fillMaxSize(),
            primaryColor = Color(0xFF673AB7),    // Deeper purple color
            secondaryColor = Color(0xffffb727) // Lighter purple color
        )

        // 2. Place your other content on top.
        Text(
            text = "Hello, Fluid World!",
            fontSize = 32.sp,
            color = Color.White
        )
    }
}