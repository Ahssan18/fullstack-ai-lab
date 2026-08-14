package com.example.composezerotohero.ui.Navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composezerotohero.ui.presentation.home.HomeScreen
import com.example.composezerotohero.ui.presentation.profile.ProfileScreen
@ExperimentalMaterial3Api
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Screens.Home.route){

        composable(Screens.Profile.route) {backStackEntry->
            val userId = backStackEntry.arguments?.getString("userId")
            val name = backStackEntry.arguments?.getString("name")
            ProfileScreen(navController,userId,name)
        }

        composable(Screens.Home.route) {
            HomeScreen(navController)
        }
    }
}