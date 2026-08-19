package com.example.composezerotohero.data.repository

import com.example.composezerotohero.domain.model.User
import com.example.composezerotohero.domain.repository.LoginRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor() : LoginRepository {
    override suspend fun login(email: String, password: String): Result<User> {
        return runCatching {
            delay(2000) // Simulate network delay
            if (email == "test@example.com" && password == "password") {
                User(email, "fake-jwt-token")
            } else {
                throw Exception("Invalid credentials")
            }
        }
    }
}
