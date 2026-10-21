package org.train.projecto.presentation.screens.loginScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.train.projecto.presentation.uiStates.LoginUiState
import org.train.projecto.presentation.localization.LocalStrings

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onNicknameChanged: (String) -> Unit,
    onLogin: () -> Unit
) {
    val strings = LocalStrings.current
    val colors = MaterialTheme.colorScheme

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .imePadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 28.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Decorative Poké Ball
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(colors.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "◉",
                    fontSize = 72.sp,
                    color = colors.primary
                )
            }

            Spacer(Modifier.height(28.dp))

            // App name
            Text(
                text = strings.appName,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 3.sp,
                color = colors.primary
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = strings.journeyStarts,
                style = MaterialTheme.typography.bodyLarge,
                color = colors.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(44.dp))

            // Welcome
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = strings.welcomeTrainer,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = colors.onBackground
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = strings.chooseNickname,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.onSurfaceVariant
                )
            }

            Spacer(Modifier.height(24.dp))

            // Nickname field
            OutlinedTextField(
                value = uiState.nickname,
                onValueChange = onNicknameChanged,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(strings.nickname)
                },
                placeholder = {
                    Text(strings.nicknameExample)
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                isError = uiState.error != null,
                enabled = !uiState.isLoading,
                supportingText = {
                    if (uiState.error != null) {
                        Text(
                            text = uiState.error,
                            color = colors.error
                        )
                    } else {
                        Text(strings.nicknameHint)
                    }
                }
            )

            Spacer(Modifier.height(24.dp))

            // Continue button
            Button(
                onClick = onLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = uiState.nickname.isNotBlank() &&
                        !uiState.isLoading,
                shape = RoundedCornerShape(16.dp)
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(22.dp),
                        strokeWidth = 2.dp,
                        color = colors.onPrimary
                    )

                    Spacer(Modifier.width(12.dp))

                    Text(strings.gettingReady)
                } else {
                    Text(
                        text = strings.startAdventure,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(Modifier.width(8.dp))

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            Text(
                text = strings.catchphrase,
                style = MaterialTheme.typography.labelLarge,
                color = colors.onSurfaceVariant
            )
        }
    }
}
