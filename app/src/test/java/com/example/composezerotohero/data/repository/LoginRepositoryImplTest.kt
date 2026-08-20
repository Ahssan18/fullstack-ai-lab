package com.example.composezerotohero.data.repository

import com.example.composezerotohero.data.remote.dto.LoginResponse
import com.example.composezerotohero.data.remote.source.LoginRemoteDataSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LoginRepositoryImplTest {

    private lateinit var repository: LoginRepositoryImpl
    private val remoteDataSource: LoginRemoteDataSource = mockk()

    @Before
    fun setUp() {
        repository = LoginRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `Correct credentials return success`() = runTest {
        coEvery { remoteDataSource.login(any()) } returns LoginResponse("test@example.com", "fake-jwt-token")
        
        val result = repository.login("test@example.com", "password")
        assertTrue(result.isSuccess)
        assertTrue(result.getOrNull()?.token == "fake-jwt-token")
    }

    @Test
    fun `Incorrect credentials return failure`() = runTest {
        coEvery { remoteDataSource.login(any()) } throws Exception("Invalid credentials")
        
        val result = repository.login("wrong@example.com", "wrong")
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull()?.message == "Invalid credentials")
    }
}
