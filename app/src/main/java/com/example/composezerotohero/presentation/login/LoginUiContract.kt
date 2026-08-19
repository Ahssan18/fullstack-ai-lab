package com.example.composezerotohero.presentation.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)

sealed class LoginUiIntent {
    data class EnteredEmail(val email: String) : LoginUiIntent()
    data class EnteredPassword(val password: String) : LoginUiIntent()
    object TogglePasswordVisibility : LoginUiIntent()
    object LoginClicked : LoginUiIntent()
}

sealed class LoginUiEffect {
    object NavigateToHome : LoginUiEffect()
    data class ShowSnackBar(val message: String) : LoginUiEffect()
}
