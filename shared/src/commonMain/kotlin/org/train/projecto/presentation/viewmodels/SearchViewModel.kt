package org.train.projecto.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.train.projecto.domain.models.PokemonPreview
import org.train.projecto.domain.repositories.PokemonRepository
import org.train.projecto.domain.repositories.SettingsRepository
import org.train.projecto.presentation.uiStates.SearchUiState
import kotlin.time.Duration.Companion.milliseconds

class SearchViewModel(private val pokemonRepository: PokemonRepository): ViewModel() {

    private val searchQuery = MutableStateFlow("") //Це query, яку вводить користувач

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private val searchedPokemons = searchQuery //Тут ми query обробляємо для методу search
        .debounce(300.milliseconds)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            if (query.isEmpty()) {
                flowOf(emptyList<PokemonPreview>())
            } else {
                pokemonRepository.getPokemonPreviewsByQuery(query)
            }

        }

    val uiState: StateFlow<SearchUiState> = combine(
        searchQuery, searchedPokemons
    ){
        query, searchedPokemons ->
        SearchUiState.Success(
            query, searchedPokemons
        ) as SearchUiState
    }.
        catch { emit(SearchUiState.Error(it.message ?: "Unexpected error")) }

        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SearchUiState.Loading


    )

    fun onQueryChange(query: String) {
        searchQuery.value = query
    }

}