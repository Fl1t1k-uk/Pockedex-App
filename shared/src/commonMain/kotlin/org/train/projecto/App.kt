package org.train.projecto

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.viewmodel.koinViewModel
import org.train.projecto.presentation.navigation.AppNavigation
import org.train.projecto.presentation.localization.LocalStrings
import org.train.projecto.presentation.localization.stringsFor
import org.train.projecto.presentation.LightColorScheme
import org.train.projecto.presentation.DarkColorScheme
import org.train.projecto.presentation.uiStates.SettingsUiState
import org.train.projecto.presentation.viewmodels.SettingsViewModel

@Composable
fun App() {
    val settingsViewModel: SettingsViewModel = koinViewModel()
    val settingsState by settingsViewModel.uiState.collectAsState()

    val isDarkTheme = when (val state = settingsState) {
        is SettingsUiState.Success -> state.settings.isDarkTheme
        SettingsUiState.Loading,
        is SettingsUiState.Error -> false
    }
    val languageCode = (settingsState as? SettingsUiState.Success)
        ?.settings
        ?.languageCode
        ?: "uk"

    CompositionLocalProvider(LocalStrings provides stringsFor(languageCode)) {
        MaterialTheme(
            colorScheme = if (isDarkTheme) DarkColorScheme else LightColorScheme
        ) {
            AppNavigation(settingsViewModel = settingsViewModel)
        }
    }
}
