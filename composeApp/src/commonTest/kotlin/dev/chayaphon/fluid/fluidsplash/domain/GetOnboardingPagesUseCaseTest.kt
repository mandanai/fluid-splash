package dev.chayaphon.fluid.fluidsplash.domain

import dev.chayaphon.fluid.fluidsplash.domain.usecase.GetOnboardingPagesUseCase
import dev.chayaphon.fluid.fluidsplash.fake.FakeOnboardingRepository
import dev.chayaphon.fluid.fluidsplash.fake.SamplePages
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetOnboardingPagesUseCaseTest {

    @Test
    fun emitsThePagesProvidedByTheRepository() = runTest {
        val useCase = GetOnboardingPagesUseCase(FakeOnboardingRepository(SamplePages))

        assertEquals(SamplePages, useCase().first())
    }
}
