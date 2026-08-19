package com.example.composezerotohero.domain.repository

import com.example.composezerotohero.domain.model.User

interface LoginRepository {
    suspend fun login(email: String, password: String): Result<User>
}
