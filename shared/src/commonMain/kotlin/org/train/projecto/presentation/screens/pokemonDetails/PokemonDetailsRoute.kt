package org.train.projecto.presentation.screens.pokemonDetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import org.train.projecto.presentation.viewmodels.PokemonDetailsViewModel

@Composable
fun PokemonDetailsRoute(
    pokemonId: Int,
    onBack: () -> Unit,
    viewModel: PokemonDetailsViewModel = koinViewModel(
        parameters = { parametersOf(pokemonId) }
    )
) {
    val uiState by viewModel.uiState.collectAsState()

    PokemonDetailsScreen(
        uiState = uiState,
        onBack = onBack,
        onToggleCollection = viewModel::toggleInCollection
    )
}