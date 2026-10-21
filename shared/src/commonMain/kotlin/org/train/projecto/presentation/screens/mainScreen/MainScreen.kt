package org.train.projecto.presentation.screens.mainScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.train.projecto.presentation.uiStates.MainUiState

@Composable
fun MainScreen(
    uiState: MainUiState,
    onSearchQueryChanged: (String) -> Unit,
    onOpenCollection: () -> Unit,
    onOpenPokemon: (Int) -> Unit,
    onOpenPokedex: () -> Unit,
    onOpenSettings: () -> Unit
) {
    when (uiState) {
        MainUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is MainUiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(uiState.message)
            }
        }

        is MainUiState.Success -> {
            MainContent(
                state = uiState,
                onSearchQueryChanged = onSearchQueryChanged,
                onOpenCollection = onOpenCollection,
                onOpenPokemon = onOpenPokemon,
                onOpenPokedex = onOpenPokedex,
                onOpenSettings = onOpenSettings
            )
        }
    }
}