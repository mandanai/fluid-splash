package dev.chayaphon.fluid.fluidsplash.presentation.introduce

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.chayaphon.fluid.fluidsplash.domain.usecase.GetOnboardingPagesUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class IntroduceViewModel(
    getOnboardingPages: GetOnboardingPagesUseCase,
) : ViewModel() {

    val uiState: StateFlow<IntroduceUiState> = getOnboardingPages()
        .map { pages -> IntroduceUiState(pages = pages.map { it.toUi() }) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
            initialValue = IntroduceUiState(),
        )

    private companion object {
        const val STOP_TIMEOUT_MILLIS = 5_000L
    }
}
