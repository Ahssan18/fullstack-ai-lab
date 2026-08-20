package com.example.composezerotohero.data.remote.source

import com.example.composezerotohero.data.remote.api.LoginApiService
import com.example.composezerotohero.data.remote.dto.LoginRequest
import com.example.composezerotohero.data.remote.dto.LoginResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LoginRemoteDataSourceImplTest {

    private lateinit var dataSource: LoginRemoteDataSourceImpl
    private val apiService: LoginApiService = mockk()

    @Before
    fun setUp() {
        dataSource = LoginRemoteDataSourceImpl(apiService)
    }

    @Test
    fun `login calls apiService and returns response`() = runTest {
        val request = LoginRequest("test@test.com", "password")
        val response = LoginResponse("test@test.com", "token")
        coEvery { apiService.login(request) } returns response

        val result = dataSource.login(request)

        assertEquals(response, result)
    }
}
