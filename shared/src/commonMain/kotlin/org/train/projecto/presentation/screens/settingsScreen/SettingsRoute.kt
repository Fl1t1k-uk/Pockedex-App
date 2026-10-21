package org.train.projecto.presentation.screens.settingsScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.viewmodel.koinViewModel
import org.train.projecto.presentation.viewmodels.SettingsViewModel

@Composable
fun SettingsRoute(
    onBack: () -> Unit,
    viewModel: SettingsViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    SettingsScreen(
        uiState = uiState,
        onBack = onBack,
        onToggleTheme = viewModel::toggleTheme,
        onChangeNickname = viewModel::changeNickname,
        onChangeLanguage = viewModel::changeLanguage
    )
}