package com.example.messagingapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.messagingapp.screens.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route){
        composable(AppScreens.SplashScreen.route){
            SplashScreen(navController)

        }
        composable(AppScreens.HomeScreen.route){
            HomeScreen(navController)
        }
        composable(AppScreens.ChatScreen.route){
            ChatScreen(navController)
        }
        composable(AppScreens.HomeScreen.route){
            HomeScreen(navController)
        }
        composable(AppScreens.LoginScreen.route){
           LoginScreen(navController)
        }
        composable(AppScreens.SignUpScreen.route){
            SignUpScreen(navController)
        }
        composable(AppScreens.ConfirmEmailScreen.route){
            ConfirmEmailScreen(navController)
        }
        composable(AppScreens.ForgottenPasswordScreen.route){
            ForgottenPasswordScreen(navController)
        }
    }
}