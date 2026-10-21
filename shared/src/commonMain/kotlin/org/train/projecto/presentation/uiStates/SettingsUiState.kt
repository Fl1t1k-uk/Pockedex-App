package org.train.projecto.presentation.uiStates

import org.train.projecto.data.local.datastore.AppSettings
import org.train.projecto.data.local.datastore.SettingsDataStore

sealed interface SettingsUiState {
    object Loading: SettingsUiState
    data class Success(
        val settings: AppSettings
    ): SettingsUiState
    data class Error(val error: String): SettingsUiState
}