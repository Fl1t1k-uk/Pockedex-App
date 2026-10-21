package org.train.projecto.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.utils.io.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.train.projecto.domain.repositories.PokemonRepository
import org.train.projecto.presentation.uiStates.CollectionUiState

class CollectionViewModel(private val pokemonRepository: PokemonRepository): ViewModel() {

    private val _uiState = MutableStateFlow<CollectionUiState>(CollectionUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        loadPokemons()
    }
    private fun loadPokemons(){
        viewModelScope.launch {
            _uiState.value = CollectionUiState.Loading
            try{
                pokemonRepository.getPokemonsInCollection().collect { pokemons ->
                    _uiState.value = CollectionUiState.Success(pokemons)
                }
            } catch (e: CancellationException){
                throw e
            } catch (e: Exception){
                _uiState.value = CollectionUiState.Error(e.message ?: "Unexpected error")
            }
        }
    }
}