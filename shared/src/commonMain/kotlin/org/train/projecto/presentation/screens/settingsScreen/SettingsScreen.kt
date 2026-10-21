package org.train.projecto.presentation.screens.settingsScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.train.projecto.presentation.uiStates.SettingsUiState

@Composable
fun SettingsScreen(
    uiState: SettingsUiState,
    onBack: () -> Unit,
    onToggleTheme: () -> Unit,
    onChangeNickname: (String) -> Unit,
    onChangeLanguage: (String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (uiState) {
            SettingsUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is SettingsUiState.Error -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    TextButton(onClick = onBack) {
                        Text("← Back")
                    }

                    Text(
                        text = uiState.error,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            is SettingsUiState.Success -> {
                SettingsContent(
                    settings = uiState.settings,
                    onBack = onBack,
                    onToggleTheme = onToggleTheme,
                    onChangeNickname = onChangeNickname,
                    onChangeLanguage = onChangeLanguage
                )
            }
        }
    }
}
