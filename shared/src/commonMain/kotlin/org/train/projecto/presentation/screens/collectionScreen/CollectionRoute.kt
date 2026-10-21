package org.train.projecto.presentation.screens.collectionScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.viewmodel.koinViewModel
import org.train.projecto.presentation.viewmodels.CollectionViewModel
import org.train.projecto.presentation.viewmodels.LoginViewModel

@Composable
fun CollectionRoute(
    onBack: () -> Unit,
    onOpenPokemon: (Int) -> Unit,
    viewModel: CollectionViewModel = koinViewModel()

) {
    val uiState by viewModel.uiState.collectAsState()

    CollectionScreen(
        onBack = onBack,
        uiState = uiState,
        onOpenPokemon = onOpenPokemon
    )
}