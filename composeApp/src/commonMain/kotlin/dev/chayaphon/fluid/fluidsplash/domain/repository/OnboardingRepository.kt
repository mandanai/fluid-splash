package dev.chayaphon.fluid.fluidsplash.domain.repository

import dev.chayaphon.fluid.fluidsplash.domain.model.OnboardingPage
import kotlinx.coroutines.flow.Flow

interface OnboardingRepository {
    fun getPages(): Flow<List<OnboardingPage>>
}
