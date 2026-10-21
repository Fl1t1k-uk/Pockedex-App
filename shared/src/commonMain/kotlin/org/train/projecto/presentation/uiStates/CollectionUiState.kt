package org.train.projecto.presentation.uiStates

import org.train.projecto.domain.models.PokemonPreview

sealed interface CollectionUiState {
    data object Loading: CollectionUiState
    data class Success(val pokemons: List<PokemonPreview>): CollectionUiState
    data class Error(val message: String): CollectionUiState
}