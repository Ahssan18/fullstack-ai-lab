package com.example.composezerotohero.domain.validation

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LoginValidatorTest {

    private lateinit var validator: LoginValidator

    @Before
    fun setUp() {
        validator = LoginValidator()
    }

    @Test
    fun `Empty email returns failure`() {
        val result = validator.validateEmail("")
        assertFalse(result.successful)
        assertTrue(result.errorMessage == "Email cannot be empty")
    }

    @Test
    fun `Invalid email format returns failure`() {
        val result = validator.validateEmail("invalid-email")
        assertFalse(result.successful)
        assertTrue(result.errorMessage == "That's not a valid email")
    }

    @Test
    fun `Valid email returns success`() {
        val result = validator.validateEmail("test@example.com")
        assertTrue(result.successful)
    }

    @Test
    fun `Empty password returns failure`() {
        val result = validator.validatePassword("")
        assertFalse(result.successful)
        assertTrue(result.errorMessage == "Password cannot be empty")
    }

    @Test
    fun `Short password returns failure`() {
        val result = validator.validatePassword("12345")
        assertFalse(result.successful)
        assertTrue(result.errorMessage == "Password must be at least 6 characters")
    }

    @Test
    fun `Valid password returns success`() {
        val result = validator.validatePassword("123456")
        assertTrue(result.successful)
    }
}
