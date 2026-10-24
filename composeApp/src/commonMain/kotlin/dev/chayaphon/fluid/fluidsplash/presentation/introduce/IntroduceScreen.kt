package dev.chayaphon.fluid.fluidsplash.presentation.introduce

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.chayaphon.fluid.fluidsplash.presentation.introduce.components.OnboardingCard
import dev.chayaphon.fluid.fluidsplash.presentation.resources.FluidResources
import dev.chayaphon.lib.fluid.ui.FluidCarousel

@Composable
fun IntroduceRoute(viewModel: IntroduceViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    IntroduceScreen(
        uiState = uiState,
        onCallToActionClick = {},
    )
}

@Composable
fun IntroduceScreen(
    uiState: IntroduceUiState,
    onCallToActionClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val pages = uiState.pages.map { page ->
        @Composable {
            OnboardingCard(
                page = page,
                onCallToActionClick = onCallToActionClick,
            )
        }
    }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        FluidCarousel(children = pages)
    }
}

@Preview
@Composable
private fun IntroduceScreenPreview() {
    IntroduceScreen(
        uiState = IntroduceUiState(
            pages = listOf(
                OnboardingPageUi(
                    title = "Breathe Life\ninto Calm Beginnings",
                    subtitle = "Rise refreshed to gentle sounds of nature.",
                    callToAction = null,
                    background = FluidResources.Backgrounds.RED,
                    illustration = FluidResources.Illustrations.RED,
                ),
                OnboardingPageUi(
                    title = "Victory on your way,\nresounding your dream,\ncustom your view",
                    subtitle = null,
                    callToAction = "Learn More",
                    background = FluidResources.Backgrounds.PURPLE,
                    illustration = FluidResources.Illustrations.PURPLE,
                ),
            )
        ),
        onCallToActionClick = {},
    )
}
