package dev.chayaphon.fluid.fluidsplash.di

import dev.chayaphon.fluid.fluidsplash.data.repository.OnboardingRepositoryImpl
import dev.chayaphon.fluid.fluidsplash.domain.repository.OnboardingRepository
import dev.chayaphon.fluid.fluidsplash.domain.usecase.GetOnboardingPagesUseCase

/**
 * Manual dependency graph for the demo app. Created once in `App()` and handed to the
 * navigation graph, which builds each ViewModel from it.
 */
class AppContainer {
    private val onboardingRepository: OnboardingRepository by lazy { OnboardingRepositoryImpl() }

    val getOnboardingPagesUseCase: GetOnboardingPagesUseCase by lazy {
        GetOnboardingPagesUseCase(onboardingRepository)
    }
}
