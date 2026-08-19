package com.example.composezerotohero.domain.usecase

import com.example.composezerotohero.domain.model.User
import com.example.composezerotohero.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        return repository.login(email, password)
    }
}
