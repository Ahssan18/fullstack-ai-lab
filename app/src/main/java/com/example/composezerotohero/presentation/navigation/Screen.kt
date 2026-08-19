package com.example.composezerotohero.presentation.navigation

sealed class Screen(val route: String) {
    data object Login : Screen("Login")
}
