package org.train.projecto.presentation.screens.settingsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.train.projecto.data.local.datastore.AppSettings

@Composable
fun SettingsContent(
    settings: AppSettings,
    onBack: () -> Unit,
    onToggleTheme: () -> Unit,
    onChangeNickname: (String) -> Unit,
    onChangeLanguage: (String) -> Unit
) {
    var nickname by remember(settings.nickname) {
        mutableStateOf(settings.nickname)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TextButton(onClick = onBack) {
            Text("← Back")
        }

        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(28.dp))

        Text(
            text = "Profile",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = nickname,
            onValueChange = { nickname = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            label = { Text("Nickname") }
        )

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = {
                onChangeNickname(nickname.trim())
            },
            enabled = nickname.trim().isNotBlank() &&
                    nickname.trim() != settings.nickname
        ) {
            Text("Save nickname")
        }

        Spacer(Modifier.height(28.dp))

        Text(
            text = "Appearance",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Dark theme")
                Text(
                    text = if (settings.isDarkTheme) "Enabled" else "Disabled",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Switch(
                checked = settings.isDarkTheme,
                onCheckedChange = { onToggleTheme() }
            )
        }

        Spacer(Modifier.height(28.dp))

        Text(
            text = "Language",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                selected = settings.languageCode == "uk",
                onClick = { onChangeLanguage("uk") },
                label = { Text("Українська") }
            )

            FilterChip(
                selected = settings.languageCode == "en",
                onClick = { onChangeLanguage("en") },
                label = { Text("English") }
            )
        }
    }
}