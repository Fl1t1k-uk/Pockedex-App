package org.train.projecto.presentation.screens.mainScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.viewmodel.koinViewModel
import org.train.projecto.presentation.viewmodels.MainViewModel

@Composable
fun MainRoute(
    onOpenPokemon: (Int) -> Unit,
    onOpenCollection: () -> Unit,
    onOpenPokedex: () -> Unit,
    onOpenSettings: () -> Unit,
    viewModel: MainViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    MainScreen(
        uiState = uiState,
        onSearchQueryChanged = viewModel::onSearchQueryChange,
        onOpenCollection = onOpenCollection,
        onOpenPokemon = onOpenPokemon,
        onOpenPokedex = onOpenPokedex,
        onOpenSettings = onOpenSettings
    )
}