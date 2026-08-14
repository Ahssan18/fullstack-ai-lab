package com.example.composezerotohero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.composezerotohero.ui.app.MyApp
import com.example.composezerotohero.ui.theme.ComposeZeroToHeroTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeZeroToHeroTheme {
                MyApp()
            }
        }
    }
}

