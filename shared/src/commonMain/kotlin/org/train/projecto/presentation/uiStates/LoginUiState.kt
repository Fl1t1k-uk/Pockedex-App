package org.train.projecto.presentation.uiStates

data class LoginUiState(
    val nickname: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)


sealed interface LoginEvent{
    data object LoginSuccess: LoginEvent
}