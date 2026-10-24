package dev.chayaphon.fluid.fluidsplash.presentation.introduce

import dev.chayaphon.fluid.fluidsplash.domain.usecase.GetOnboardingPagesUseCase
import dev.chayaphon.fluid.fluidsplash.fake.FakeOnboardingRepository
import dev.chayaphon.fluid.fluidsplash.fake.SamplePages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class IntroduceViewModelTest {

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun createViewModel() = IntroduceViewModel(
        GetOnboardingPagesUseCase(FakeOnboardingRepository(SamplePages))
    )

    @Test
    fun startsEmptyUntilSomethingCollects() {
        assertTrue(createViewModel().uiState.value.pages.isEmpty())
    }

    @Test
    fun exposesMappedPagesOnceCollected() = runTest {
        val viewModel = createViewModel()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.collect {}
        }

        assertEquals(SamplePages.map { it.toUi() }, viewModel.uiState.value.pages)
    }
}
