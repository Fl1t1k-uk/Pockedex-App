package org.train.projecto.presentation.uiStates

import androidx.room3.Query
import org.train.projecto.domain.models.PokemonPreview

sealed interface SearchUiState {
    data object Loading: SearchUiState
    data class Success(
        val searchQuery: String,
        val results: List<PokemonPreview>,
    ): SearchUiState
    data class Error(
        val message: String
    ): SearchUiState
}