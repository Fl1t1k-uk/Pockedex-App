package org.train.projecto.presentation.uiStates

sealed interface DownloadUiState {
    data object Loading : DownloadUiState
    data object Done : DownloadUiState
    data class Error(val message: String) : DownloadUiState
}