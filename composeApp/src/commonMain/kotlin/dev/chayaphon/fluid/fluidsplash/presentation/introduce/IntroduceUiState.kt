package dev.chayaphon.fluid.fluidsplash.presentation.introduce

import org.jetbrains.compose.resources.DrawableResource

data class IntroduceUiState(
    val pages: List<OnboardingPageUi> = emptyList(),
)

/**
 * A page ready to render. Carries [DrawableResource] handles rather than painters, so the
 * ViewModel never has to touch composition.
 */
data class OnboardingPageUi(
    val title: String,
    val subtitle: String?,
    val callToAction: String?,
    val background: DrawableResource,
    val illustration: DrawableResource,
)
