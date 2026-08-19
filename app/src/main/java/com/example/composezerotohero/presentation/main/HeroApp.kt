package com.example.composezerotohero.presentation.main

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.composezerotohero.presentation.navigation.NavGraph

@ExperimentalMaterial3Api
@Composable
fun HeroApp() {
    val navController = rememberNavController()
    NavGraph(navController)
}