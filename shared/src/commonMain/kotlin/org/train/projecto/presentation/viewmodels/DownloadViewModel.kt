package org.train.projecto.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.train.projecto.domain.repositories.PokemonRepository
import org.train.projecto.domain.repositories.SettingsRepository
import org.train.projecto.presentation.uiStates.DownloadUiState

class DownloadViewModel(
    private val pokemonRepository: PokemonRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<DownloadUiState>(DownloadUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        download()
    }

    fun retry() {
        if (_uiState.value is DownloadUiState.Error) download()
    }

    private fun download() {
        viewModelScope.launch {
            _uiState.value = DownloadUiState.Loading

            val result = pokemonRepository.downloadAllPokemons()
            if (result.isSuccess) {
                settingsRepository.completeDownloading()
                _uiState.value = DownloadUiState.Done
            } else {
                _uiState.value = DownloadUiState.Error(
                    result.exceptionOrNull()?.message ?: "Failed to download Pokémon"
                )
            }
        }
    }
}