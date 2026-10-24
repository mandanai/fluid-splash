package dev.chayaphon.fluid.fluidsplash.data.repository

import dev.chayaphon.fluid.fluidsplash.domain.model.OnboardingPage
import dev.chayaphon.fluid.fluidsplash.domain.model.PageTheme
import dev.chayaphon.fluid.fluidsplash.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/** Serves the demo's built-in onboarding content. */
class OnboardingRepositoryImpl : OnboardingRepository {

    override fun getPages(): Flow<List<OnboardingPage>> = flowOf(PAGES)

    private companion object {
        val PAGES = listOf(
            OnboardingPage(
                title = "Breathe Life\ninto Calm Beginnings",
                subtitle = "Rise refreshed to gentle sounds of nature that softly guide your morning awakening.",
                callToAction = null,
                theme = PageTheme.Red,
            ),
            OnboardingPage(
                title = "Let Breath Become\nYour Mind's Gentle Reset",
                subtitle = "Achieve stress reduction and cognitive enhancement through peer-reviewed breathing protocols—continuously available.",
                callToAction = null,
                theme = PageTheme.Yellow,
            ),
            OnboardingPage(
                title = "Drift on Waves of Gentle\nNarratives into Peaceful Rest",
                subtitle = "Cultivate restorative sleep through therapeutic storytelling and guided mindfulness.",
                callToAction = null,
                theme = PageTheme.Blue,
            ),
            OnboardingPage(
                title = "Victory on your way,\nresounding your dream,\ncustom your view",
                subtitle = null,
                callToAction = "Learn More",
                theme = PageTheme.Purple,
            ),
        )
    }
}
