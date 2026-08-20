package com.example.composezerotohero.presentation.login

import app.cash.turbine.test
import com.example.composezerotohero.domain.model.User
import com.example.composezerotohero.domain.usecase.LoginUseCase
import com.example.composezerotohero.domain.validation.LoginValidator
import com.example.composezerotohero.domain.validation.ValidationResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel
    private val loginUseCase: LoginUseCase = mockk()
    private val loginValidator: LoginValidator = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = LoginViewModel(loginUseCase, loginValidator)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `1 Initial state is correct`() = runTest {
        val state = viewModel.state.value
        assertEquals("", state.email)
        assertEquals("", state.password)
        assertFalse(state.isLoading)
        assertFalse(state.isSuccess)
        assertNull(state.error)
    }

    @Test
    fun `2 Email validation failure updates error state`() = runTest {
        every { loginValidator.validateEmail(any()) } returns ValidationResult(false, "Invalid Email")
        every { loginValidator.validatePassword(any()) } returns ValidationResult(true)

        viewModel.onIntent(LoginUiIntent.EnteredEmail("wrong"))
        viewModel.onIntent(LoginUiIntent.LoginClicked)

        assertEquals("Invalid Email", viewModel.state.value.error)
        coVerify(exactly = 0) { loginUseCase(any(), any()) }
    }

    @Test
    fun `3 Password validation failure updates error state`() = runTest {
        every { loginValidator.validateEmail(any()) } returns ValidationResult(true)
        every { loginValidator.validatePassword(any()) } returns ValidationResult(false, "Short Password")

        viewModel.onIntent(LoginUiIntent.EnteredPassword("123"))
        viewModel.onIntent(LoginUiIntent.LoginClicked)

        assertEquals("Short Password", viewModel.state.value.error)
        coVerify(exactly = 0) { loginUseCase(any(), any()) }
    }

    @Test
    fun `4 Login starts sets isLoading to true`() = runTest {
        every { loginValidator.validateEmail(any()) } returns ValidationResult(true)
        every { loginValidator.validatePassword(any()) } returns ValidationResult(true)
        coEvery { loginUseCase(any(), any()) } coAnswers {
            delay(1000)
            Result.success(User("test@test.com", "token"))
        }

        viewModel.onIntent(LoginUiIntent.LoginClicked)
        
        assertTrue(viewModel.state.value.isLoading)
        
        advanceUntilIdle()
        
        assertFalse(viewModel.state.value.isLoading)
        assertTrue(viewModel.state.value.isSuccess)
    }

    @Test
    fun `5 Successful login updates state and sends effect`() = runTest {
        every { loginValidator.validateEmail(any()) } returns ValidationResult(true)
        every { loginValidator.validatePassword(any()) } returns ValidationResult(true)
        coEvery { loginUseCase(any(), any()) } returns Result.success(User("test@test.com", "token"))

        viewModel.effect.test {
            viewModel.onIntent(LoginUiIntent.LoginClicked)
            advanceUntilIdle()
            
            assertTrue(viewModel.state.value.isSuccess)
            assertEquals(LoginUiEffect.NavigateToHome, awaitItem())
        }
    }

    @Test
    fun `6 Failed login updates state and sends snackbar effect`() = runTest {
        every { loginValidator.validateEmail(any()) } returns ValidationResult(true)
        every { loginValidator.validatePassword(any()) } returns ValidationResult(true)
        coEvery { loginUseCase(any(), any()) } returns Result.failure(Exception("Network Error"))

        viewModel.effect.test {
            viewModel.onIntent(LoginUiIntent.LoginClicked)
            advanceUntilIdle()
            
            assertEquals("Network Error", viewModel.state.value.error)
            val effect = awaitItem()
            assertTrue(effect is LoginUiEffect.ShowSnackBar)
            assertEquals("Network Error", (effect as LoginUiEffect.ShowSnackBar).message)
        }
    }

    @Test
    fun `7 Duplicate login while Loading is ignored`() = runTest {
        every { loginValidator.validateEmail(any()) } returns ValidationResult(true)
        every { loginValidator.validatePassword(any()) } returns ValidationResult(true)
        coEvery { loginUseCase(any(), any()) } coAnswers {
            testDispatcher.scheduler.advanceTimeBy(2000)
            Result.success(User("test@test.com", "token"))
        }

        viewModel.onIntent(LoginUiIntent.LoginClicked)
        // Now it's loading
        
        viewModel.onIntent(LoginUiIntent.LoginClicked) // Second click
        
        advanceUntilIdle()
        
        // Verify use case was only called once
        coVerify(exactly = 1) { loginUseCase(any(), any()) }
    }

    @Test
    fun `8 Repository exception is handled by onFailure`() = runTest {
        every { loginValidator.validateEmail(any()) } returns ValidationResult(true)
        every { loginValidator.validatePassword(any()) } returns ValidationResult(true)
        // Throwing a raw exception to test runCatching in ViewModel
        coEvery { loginUseCase(any(), any()) } throws RuntimeException("Unexpected Crash")

        viewModel.effect.test {
            viewModel.onIntent(LoginUiIntent.LoginClicked)
            advanceUntilIdle()
            
            assertFalse(viewModel.state.value.isLoading)
            assertEquals("Unexpected Crash", viewModel.state.value.error)
            val effect = awaitItem()
            assertTrue(effect is LoginUiEffect.ShowSnackBar)
            assertEquals("Unexpected Crash", (effect as LoginUiEffect.ShowSnackBar).message)
        }
    }
}
