package com.example.composezerotohero.data.repository

import com.example.composezerotohero.data.remote.dto.LoginRequest
import com.example.composezerotohero.data.remote.source.LoginRemoteDataSource
import com.example.composezerotohero.domain.model.User
import com.example.composezerotohero.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val remoteDataSource: LoginRemoteDataSource
) : LoginRepository {
    override suspend fun login(email: String, password: String): Result<User> {
        return runCatching {
            val response = remoteDataSource.login(
                LoginRequest(email, password)
            )
            User(
                email = response.email,
                token = response.token
            )
        }
    }
}
