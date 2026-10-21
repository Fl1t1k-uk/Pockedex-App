package org.train.projecto.presentation.uiStates

import org.train.projecto.domain.models.PokemonPreview

sealed interface MainUiState {
    data object Loading: MainUiState
    data class Success(
        val nickname: String, //В мене не буде user, а nickname просто буде братися із datastore
        val recentlyDiscover: List<PokemonPreview>,
        val collectionCount: Int,
        val searchQuery: String = "",
        val searchResults: List<PokemonPreview> = emptyList()
    ): MainUiState
    data class Error(
        val message: String
    ): MainUiState
}