package dev.chayaphon.fluid.fluidsplash.presentation.introduce

import dev.chayaphon.fluid.fluidsplash.domain.model.OnboardingPage
import dev.chayaphon.fluid.fluidsplash.domain.model.PageTheme
import dev.chayaphon.fluid.fluidsplash.presentation.resources.FluidResources
import org.jetbrains.compose.resources.DrawableResource

fun OnboardingPage.toUi(): OnboardingPageUi = OnboardingPageUi(
    title = title,
    subtitle = subtitle,
    callToAction = callToAction,
    background = theme.background(),
    illustration = theme.illustration(),
)

private fun PageTheme.background(): DrawableResource = when (this) {
    PageTheme.Red -> FluidResources.Backgrounds.RED
    PageTheme.Yellow -> FluidResources.Backgrounds.YELLOW
    PageTheme.Blue -> FluidResources.Backgrounds.BLUE
    PageTheme.Purple -> FluidResources.Backgrounds.PURPLE
}

private fun PageTheme.illustration(): DrawableResource = when (this) {
    PageTheme.Red -> FluidResources.Illustrations.RED
    PageTheme.Yellow -> FluidResources.Illustrations.YELLOW
    PageTheme.Blue -> FluidResources.Illustrations.BLUE
    PageTheme.Purple -> FluidResources.Illustrations.PURPLE
}
