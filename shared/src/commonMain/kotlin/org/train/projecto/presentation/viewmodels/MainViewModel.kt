package org.train.projecto.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.train.projecto.domain.models.PokemonPreview
import org.train.projecto.domain.repositories.PokemonRepository
import org.train.projecto.domain.repositories.SettingsRepository
import org.train.projecto.presentation.uiStates.MainUiState
import kotlin.time.Duration.Companion.milliseconds

class MainViewModel(private val pokemonRepository: PokemonRepository, private val settingsRepository: SettingsRepository): ViewModel() {


    private val searchQuery = MutableStateFlow("")

    private val nickname: Flow<String> =
        settingsRepository.settings.map { it.nickname }

    private val recentlyDiscover: Flow<List<PokemonPreview>> =
        pokemonRepository.getPokemonPreviewsByTime()

    private val collectionCount: Flow<Int> =
        pokemonRepository.getCollectionCount()

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private val searchResults: Flow<List<PokemonPreview>> = searchQuery
        .debounce(300.milliseconds)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            if (query.isBlank()) {
                flowOf(emptyList())
            } else {
                pokemonRepository.getPokemonPreviewsByQuery(query)
            }
        }

    val uiState: StateFlow<MainUiState> = combine( //Тому що інформація приходить з різних джерел
        nickname,
        recentlyDiscover,
        collectionCount,
        searchQuery,
        searchResults
    ) { nickname, recent, count, query, results ->
        MainUiState.Success(
            nickname = nickname,
            recentlyDiscover = recent,
            collectionCount = count,
            searchQuery = query,
            searchResults = results
        ) as MainUiState
    }
        .catch { emit(MainUiState.Error(it.message ?: "Unknown error")) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MainUiState.Loading //LOADING на почаку
        )

    fun onSearchQueryChange(query: String) {
        searchQuery.value = query
    }


}