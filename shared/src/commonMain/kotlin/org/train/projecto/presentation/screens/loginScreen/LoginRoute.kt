package org.train.projecto.presentation.screens.loginScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.viewmodel.koinViewModel
import org.train.projecto.presentation.uiStates.LoginEvent
import org.train.projecto.presentation.viewmodels.LoginViewModel

@Composable
fun LoginRoute(
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                LoginEvent.LoginSuccess -> onLoginSuccess()
            }
        }
    }

    LoginScreen(
        uiState = uiState,
        onNicknameChanged = viewModel::onNicknameChanged,
        onLogin = viewModel::login
    )
}