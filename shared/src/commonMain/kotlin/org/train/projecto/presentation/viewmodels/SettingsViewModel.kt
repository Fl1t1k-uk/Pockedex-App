package org.train.projecto.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.train.projecto.domain.repositories.SettingsRepository
import org.train.projecto.presentation.uiStates.SettingsUiState
import kotlin.coroutines.cancellation.CancellationException

class SettingsViewModel(private val settingsRepository: SettingsRepository): ViewModel() {
    private val _uiState = MutableStateFlow<SettingsUiState>(SettingsUiState.Loading)
    val uiState = _uiState.asStateFlow()


    init {
        loadScreen()
    }

    private fun loadScreen() {
        viewModelScope.launch {
            _uiState.value = SettingsUiState.Loading
            try {
                settingsRepository.settings.collect { settings ->
                    _uiState.value = SettingsUiState.Success(settings)
                }
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                _uiState.value = SettingsUiState.Error(
                    exception.message ?: "Unexpected error"
                )
            }
        }
    }

            fun toggleTheme() {
                viewModelScope.launch {
                    settingsRepository.toggleDarkTheme()
                }
            }

            fun changeNickname(nickname: String) {
                viewModelScope.launch {
                    settingsRepository.setNickname(nickname)
                }
            }

            fun changeLanguage(languageCode: String) {
                viewModelScope.launch {
                    settingsRepository.setLanguage(languageCode)
                }
            }
        }

