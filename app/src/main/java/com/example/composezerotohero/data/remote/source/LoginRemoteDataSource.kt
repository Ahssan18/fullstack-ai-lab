package com.example.composezerotohero.data.remote.source

import com.example.composezerotohero.data.remote.api.LoginApiService
import com.example.composezerotohero.data.remote.dto.LoginRequest
import com.example.composezerotohero.data.remote.dto.LoginResponse
import javax.inject.Inject

interface LoginRemoteDataSource {
    suspend fun login(request: LoginRequest): LoginResponse
}

class LoginRemoteDataSourceImpl @Inject constructor(
    private val apiService: LoginApiService
) : LoginRemoteDataSource {
    override suspend fun login(request: LoginRequest): LoginResponse {
        return apiService.login(request)
    }
}
