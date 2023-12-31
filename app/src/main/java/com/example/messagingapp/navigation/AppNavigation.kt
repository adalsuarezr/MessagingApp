package com.example.messagingapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.messagingapp.screens.*
import com.example.messagingapp.viewmodels.AuthenticationViewModel
import com.example.messagingapp.viewmodels.HomeViewModel

@Composable
fun AppNavigation(authVM: AuthenticationViewModel, homeVM:HomeViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route){

        composable(AppScreens.SplashScreen.route){
            SplashScreen(navController, authVM)
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
           LoginScreen(navController, authVM)
        }
        composable(AppScreens.SignUpScreen.route){
            SignUpScreen(navController, authVM)
        }
        composable(AppScreens.VerifyAccountScreen.route){
            VerifyAccountScreen(navController, authVM)
        }
        composable(AppScreens.ForgottenPasswordScreen.route){
            ForgottenPasswordScreen(navController, authVM)
        }
    }
}