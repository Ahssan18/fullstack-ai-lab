package com.example.composezerotohero.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composezerotohero.domain.usecase.LoginUseCase
import com.example.composezerotohero.domain.validation.LoginValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val loginValidator: LoginValidator
) : ViewModel() {

    private val _state = MutableStateFlow(LoginUiState())
    val state = _state.asStateFlow()

    private val _effect = Channel<LoginUiEffect>()
    val effect = _effect.receiveAsFlow()

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        _state.update { it.copy(isLoading = false, error = "An unexpected error occurred") }
        _effect.trySend(LoginUiEffect.ShowSnackBar(throwable.message ?: "Unknown error"))
    }

    fun onIntent(intent: LoginUiIntent) {
        when (intent) {
            is LoginUiIntent.EnteredEmail -> {
                _state.update { it.copy(email = intent.email, error = null) }
            }
            is LoginUiIntent.EnteredPassword -> {
                _state.update { it.copy(password = intent.password, error = null) }
            }
            LoginUiIntent.TogglePasswordVisibility -> {
                _state.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
            }
            LoginUiIntent.LoginClicked -> {
                login()
            }
        }
    }

    private fun login() {
        if (_state.value.isLoading) return

        val email = _state.value.email
        val password = _state.value.password

        val emailResult = loginValidator.validateEmail(email)
        val passwordResult = loginValidator.validatePassword(password)

        val hasError = listOf(
            emailResult,
            passwordResult
        ).any { !it.successful }

        if (hasError) {
            _state.update {
                it.copy(
                    error = emailResult.errorMessage ?: passwordResult.errorMessage
                )
            }
            return
        }

        _state.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch(errorHandler) {
            // Safety: use runCatching to ensure we handle any unexpected throws from the domain layer
            val result = runCatching { loginUseCase(email, password) }.getOrElse { 
                Result.failure(it) 
            }
            
            _state.update { it.copy(isLoading = false) }

            result.onSuccess {
                _state.update { it.copy(isSuccess = true) }
                _effect.send(LoginUiEffect.NavigateToHome)
            }.onFailure { error ->
                _state.update { it.copy(error = error.message ?: "Unknown error") }
                _effect.send(LoginUiEffect.ShowSnackBar(error.message ?: "Unknown error"))
            }
        }
    }
}
