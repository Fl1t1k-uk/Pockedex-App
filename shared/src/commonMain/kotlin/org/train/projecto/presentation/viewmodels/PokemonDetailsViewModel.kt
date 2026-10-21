package org.train.projecto.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.train.projecto.domain.repositories.PokemonRepository
import org.train.projecto.presentation.uiStates.PokemonDetailsUiState

class PokemonDetailsViewModel(private val pokemonRepository: PokemonRepository, private val id: Int): ViewModel() {
    private val _uiState = MutableStateFlow<PokemonDetailsUiState>(PokemonDetailsUiState.Loading)
    val uiState = _uiState.asStateFlow()


    init {
        loadPokemonDetails()
    }

    private fun loadPokemonDetails() {
        viewModelScope.launch {
            _uiState.value = PokemonDetailsUiState.Loading

            val pokemonDetails = pokemonRepository
                .getPokemonDetails(id)
                .getOrElse { error ->
                    _uiState.value = PokemonDetailsUiState.Error(
                        error.message ?: "Unexpected error occurred"
                    )
                    return@launch
                }

            pokemonRepository
                .checkIsInCollection(id)
                .collect { isInCollection ->
                    _uiState.value = PokemonDetailsUiState.Success(
                        pokemonDetails,
                        isInCollection
                    )
                }
        }

    }

    fun toggleInCollection() {
        viewModelScope.launch {
            pokemonRepository.updateInCollectionStatus(id)
        }

    }

}