package com.example.composezerotohero.domain.validation

import javax.inject.Inject

class LoginValidator @Inject constructor() {

    private val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]{2,}\$".toRegex()

    fun validateEmail(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Email cannot be empty"
            )
        }
        if (!emailRegex.matches(email)) {
            return ValidationResult(
                successful = false,
                errorMessage = "That's not a valid email"
            )
        }
        return ValidationResult(successful = true)
    }

    fun validatePassword(password: String): ValidationResult {
        if (password.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password cannot be empty"
            )
        }
        if (password.length < 6) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password must be at least 6 characters"
            )
        }
        return ValidationResult(successful = true)
    }
}

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
