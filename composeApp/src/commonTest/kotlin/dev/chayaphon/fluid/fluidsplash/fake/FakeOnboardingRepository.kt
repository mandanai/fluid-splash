package dev.chayaphon.fluid.fluidsplash.fake

import dev.chayaphon.fluid.fluidsplash.domain.model.OnboardingPage
import dev.chayaphon.fluid.fluidsplash.domain.model.PageTheme
import dev.chayaphon.fluid.fluidsplash.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeOnboardingRepository(
    private val pages: List<OnboardingPage> = SamplePages,
) : OnboardingRepository {
    override fun getPages(): Flow<List<OnboardingPage>> = flowOf(pages)
}

val SamplePages = listOf(
    OnboardingPage(
        title = "First",
        subtitle = "First subtitle",
        callToAction = null,
        theme = PageTheme.Red,
    ),
    OnboardingPage(
        title = "Last",
        subtitle = null,
        callToAction = "Go",
        theme = PageTheme.Purple,
    ),
)
