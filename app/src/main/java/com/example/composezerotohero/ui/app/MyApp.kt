package com.example.composezerotohero.ui.app

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.composezerotohero.ui.Navigation.NavGraph
@ExperimentalMaterial3Api
@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavGraph(navController)
}