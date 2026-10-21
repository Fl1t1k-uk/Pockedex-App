package org.train.projecto.presentation.uiStates

sealed interface AppStartUiState {
    data object Loading: AppStartUiState
    data object Login : AppStartUiState
    data object Download : AppStartUiState
    data object Main : AppStartUiState

}