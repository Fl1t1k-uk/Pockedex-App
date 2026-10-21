package org.train.projecto.presentation.uiStates

import org.train.projecto.domain.models.PokemonDetails

sealed interface PokemonDetailsUiState {
    data object Loading: PokemonDetailsUiState
    data class Success(
        val pokemonDetails: PokemonDetails,
        val isInCollection: Boolean
    ): PokemonDetailsUiState

    data class Error(
        val message: String
    ): PokemonDetailsUiState
}