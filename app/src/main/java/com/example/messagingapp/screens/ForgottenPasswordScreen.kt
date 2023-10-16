package com.example.messagingapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.messagingapp.viewmodels.AuthetificationViewModel

@Composable
fun ForgottenPasswordScreen(navController: NavHostController, authVM: AuthetificationViewModel) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Forgotten Password Screen")
    }
}