package dev.chayaphon.fluid.fluidsplash.presentation.loading

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoadingViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoadingUiState())
    val uiState: StateFlow<LoadingUiState> = _uiState.asStateFlow()

    fun startLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }
}
