package com.example.composezerotohero.data.repository

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LoginRepositoryImplTest {

    private lateinit var repository: LoginRepositoryImpl

    @Before
    fun setUp() {
        repository = LoginRepositoryImpl()
    }

    @Test
    fun `Correct credentials return success`() = runTest {
        val result = repository.login("test@example.com", "password")
        assertTrue(result.isSuccess)
        assertTrue(result.getOrNull()?.token == "fake-jwt-token")
    }

    @Test
    fun `Incorrect credentials return failure`() = runTest {
        val result = repository.login("wrong@example.com", "wrong")
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull()?.message == "Invalid credentials")
    }
}
