package com.example.composezerotohero.ui.Navigation

sealed class Screens(val route: String) {
    data object Home : Screens("Home")
    data object Profile : Screens("Profile/{userId}/{name}"){
        fun createRoute(userId: String,name:String) =
            "profile/$userId/$name"
    }
}