package com.example.composezerotohero.data.remote.api

import com.example.composezerotohero.data.remote.dto.LoginRequest
import com.example.composezerotohero.data.remote.dto.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {

    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    companion object {
        const val BASE_URL = "https://run.mocky.io/v3/"
    }
}
