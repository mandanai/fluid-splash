package dev.chayaphon.fluid.fluidsplash.presentation.loading

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.chayaphon.lib.fluid.ui.LiquidFillAnimation

private const val FILL_DURATION_MILLIS = 3000
private const val WAVE_HEIGHT = 35f
private val LiquidColor = Color(0xFF4FC3F7)

@Composable
fun LoadingRoute(viewModel: LoadingViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LoadingScreen(
        uiState = uiState,
        onStartLoadingClick = viewModel::startLoading,
    )
}

@Composable
fun LoadingScreen(
    uiState: LoadingUiState,
    onStartLoadingClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // Animate the progress value from 0.0f to 1.0f once loading starts.
    val progress by animateFloatAsState(
        targetValue = if (uiState.isLoading) 1.0f else 0.0f,
        animationSpec = tween(durationMillis = FILL_DURATION_MILLIS, easing = LinearEasing)
    )

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LiquidFillAnimation(
            progress = progress,
            modifier = Modifier.fillMaxSize(),
            color = LiquidColor,
            waveHeight = WAVE_HEIGHT
        )

        if (!uiState.isLoading) {
            Button(onClick = onStartLoadingClick) {
                Text("Start Loading")
            }
        }
    }
}
