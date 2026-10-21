package org.train.projecto.presentation.screens.downloadScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.viewmodel.koinViewModel
import org.train.projecto.presentation.uiStates.DownloadUiState
import org.train.projecto.presentation.viewmodels.DownloadViewModel

@Composable
fun DownloadRoute(
    onFinished: () -> Unit,
    viewModel: DownloadViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState) {
        if (uiState is DownloadUiState.Done) onFinished()
    }

    DownloadScreen(
        uiState = uiState,
        onRetry = viewModel::retry
    )
}