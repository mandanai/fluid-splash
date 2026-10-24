package dev.chayaphon.fluid.fluidsplash.domain.usecase

import dev.chayaphon.fluid.fluidsplash.domain.model.OnboardingPage
import dev.chayaphon.fluid.fluidsplash.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow

class GetOnboardingPagesUseCase(
    private val repository: OnboardingRepository,
) {
    operator fun invoke(): Flow<List<OnboardingPage>> = repository.getPages()
}
