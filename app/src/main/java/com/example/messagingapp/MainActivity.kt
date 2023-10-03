package com.example.messagingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.messagingapp.navigation.AppNavigation
import com.example.messagingapp.ui.theme.MessagingAppTheme
import com.example.messagingapp.viewmodels.AuthetificationViewModel
import com.example.messagingapp.viewmodels.HomeViewModel

class MainActivity : ComponentActivity() {
    private val authVM = AuthetificationViewModel()
    private val homeVM = HomeViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessagingAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   AppNavigation(authVM,homeVM)
                }
            }
        }
    }
}
