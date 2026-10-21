package org.train.projecto.presentation.screens.searchScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.viewmodel.koinViewModel
import org.train.projecto.presentation.viewmodels.SearchViewModel


@Composable
fun SearchRoute(
    onBack: () -> Unit,
    onOpenPokemon: (Int) -> Unit,
    viewModel: SearchViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    SearchScreen(
        uiState = uiState,
        onBack = onBack,
        onQueryChange = viewModel::onQueryChange,
        onOpenPokemon = onOpenPokemon
    )
}