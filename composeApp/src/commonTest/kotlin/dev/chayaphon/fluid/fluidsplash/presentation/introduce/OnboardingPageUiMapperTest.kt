package dev.chayaphon.fluid.fluidsplash.presentation.introduce

import dev.chayaphon.fluid.fluidsplash.domain.model.OnboardingPage
import dev.chayaphon.fluid.fluidsplash.domain.model.PageTheme
import dev.chayaphon.fluid.fluidsplash.presentation.resources.FluidResources
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class OnboardingPageUiMapperTest {

    private val expectedArtwork = mapOf(
        PageTheme.Red to (FluidResources.Backgrounds.RED to FluidResources.Illustrations.RED),
        PageTheme.Yellow to (FluidResources.Backgrounds.YELLOW to FluidResources.Illustrations.YELLOW),
        PageTheme.Blue to (FluidResources.Backgrounds.BLUE to FluidResources.Illustrations.BLUE),
        PageTheme.Purple to (FluidResources.Backgrounds.PURPLE to FluidResources.Illustrations.PURPLE),
    )

    @Test
    fun everyThemeHasExpectedArtwork() {
        // Fails when a PageTheme is added without extending this table.
        assertEquals(PageTheme.entries.toSet(), expectedArtwork.keys)
    }

    @Test
    fun mapsEachThemeToItsBackgroundAndIllustration() {
        expectedArtwork.forEach { (theme, artwork) ->
            val ui = OnboardingPage(
                title = "title",
                subtitle = "subtitle",
                callToAction = null,
                theme = theme,
            ).toUi()

            assertEquals(artwork.first, ui.background, "background for $theme")
            assertEquals(artwork.second, ui.illustration, "illustration for $theme")
        }
    }

    @Test
    fun carriesTextFieldsOver() {
        val ui = OnboardingPage(
            title = "title",
            subtitle = "subtitle",
            callToAction = null,
            theme = PageTheme.Red,
        ).toUi()

        assertEquals("title", ui.title)
        assertEquals("subtitle", ui.subtitle)
        assertNull(ui.callToAction)
    }

    @Test
    fun keepsCallToActionWhenThereIsNoSubtitle() {
        val ui = OnboardingPage(
            title = "title",
            subtitle = null,
            callToAction = "Learn More",
            theme = PageTheme.Purple,
        ).toUi()

        assertNull(ui.subtitle)
        assertEquals("Learn More", ui.callToAction)
    }
}
