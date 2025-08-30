package dev.chayaphon.fluid.fluidsplash.presentation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.chayaphon.lib.fluid.ui.LiquidFillAnimation

@Composable
fun LoadingScreen() {
    var isLoading by remember { mutableStateOf(false) }

    // Animate the progress value from 0.0f to 1.0f when isLoading is true.
    val progress by animateFloatAsState(
        targetValue = if (isLoading) 1.0f else 0.0f,
        animationSpec = tween(durationMillis = 3000, easing = LinearEasing) // 3 seconds to fill
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // 1. Call LiquidFillAnimation and pass the animated progress.
        LiquidFillAnimation(
            progress = progress,
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFF4FC3F7), // Light blue color
            waveHeight = 35f          // Make the waves a bit taller
        )

        // 2. Place other content, like a button to start the animation.
        if (!isLoading) {
            Button(onClick = { isLoading = true }) {
                Text("Start Loading")
            }
        }
    }
}