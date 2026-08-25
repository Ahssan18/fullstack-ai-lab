package com.example.composezerotohero.presentation.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composezerotohero.presentation.home.HomeScreen
import com.example.composezerotohero.presentation.login.LoginScreen

@ExperimentalMaterial3Api
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Login.route) {

        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        // Crucial for Phase 3: Prevent back navigation to Login
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen()
        }
    }
}
