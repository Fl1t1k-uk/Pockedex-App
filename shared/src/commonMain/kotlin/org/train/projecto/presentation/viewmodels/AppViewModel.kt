package org.train.projecto.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.train.projecto.domain.repositories.PokemonRepository
import org.train.projecto.domain.repositories.SettingsRepository
import org.train.projecto.presentation.uiStates.AppStartUiState
import kotlin.coroutines.cancellation.CancellationException

class AppViewModel(
    private val settingsRepository: SettingsRepository,
) : ViewModel() {

    private val _startState =
        MutableStateFlow<AppStartUiState>(AppStartUiState.Loading)

    val startState = _startState.asStateFlow()

    init {
        checkAppState()
    }

    private fun checkAppState() {
        viewModelScope.launch {
            _startState.value = AppStartUiState.Loading
            try {
                settingsRepository.settings
                    .map { settings ->
                        when {
                            !settings.isOnboardingCompleted ->
                                AppStartUiState.Login

                            !settings.isPokemonsDownloaded ->
                                AppStartUiState.Download

                            else ->
                                AppStartUiState.Main
                        }
                    }
                    .distinctUntilChanged()
                    .collect { state ->
                        _startState.value = state
                    }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {

            }
        }
    }
}