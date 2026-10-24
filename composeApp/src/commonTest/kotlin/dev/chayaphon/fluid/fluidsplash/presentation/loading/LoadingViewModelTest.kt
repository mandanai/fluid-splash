package dev.chayaphon.fluid.fluidsplash.presentation.loading

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class LoadingViewModelTest {

    @Test
    fun isNotLoadingInitially() {
        assertFalse(LoadingViewModel().uiState.value.isLoading)
    }

    @Test
    fun startLoadingSwitchesToLoading() {
        val viewModel = LoadingViewModel()

        viewModel.startLoading()

        assertTrue(viewModel.uiState.value.isLoading)
    }

    @Test
    fun startLoadingTwiceStaysLoading() {
        val viewModel = LoadingViewModel()

        viewModel.startLoading()
        viewModel.startLoading()

        assertTrue(viewModel.uiState.value.isLoading)
    }
}
