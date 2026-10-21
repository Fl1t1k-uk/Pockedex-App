package org.train.projecto.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.train.projecto.domain.repositories.SettingsRepository
import org.train.projecto.presentation.uiStates.LoginEvent
import org.train.projecto.presentation.uiStates.LoginUiState
import kotlin.coroutines.cancellation.CancellationException

class LoginViewModel(private val settingsRepository: SettingsRepository): ViewModel(){
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<LoginEvent>()
    val events = _events.asSharedFlow()

    fun onNicknameChanged(nickname: String){
        _uiState.update {
            it.copy(nickname = nickname)
        }
    }

    fun login(){
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            try{
            settingsRepository.setNickname(_uiState.value.nickname)
                settingsRepository.completeOnboarding()
                _uiState.update {
                    it.copy(
                        isLoading = false
                    )
                }
                _events.emit(LoginEvent.LoginSuccess)
            }
            catch (exception: CancellationException) {
                throw exception
            }
            catch (e: Exception){
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = "Login failed"
                    )
                }
            }



        }
    }


}