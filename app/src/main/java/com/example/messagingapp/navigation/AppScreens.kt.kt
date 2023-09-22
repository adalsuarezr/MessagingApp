package com.example.messagingapp.navigation

//Sealed classes because you know what a given value is only among a given set of options
//We don't use an enum because we need arguments
//This class is used to define the "destinations" for our navigation
sealed class AppScreens(val route:String){
    object SplashScreen: AppScreens("splash_screen")
    object LoginScreen: AppScreens("login_screen")
    object SignUpScreen: AppScreens("sign_up_screen")
    object ConfirmEmailScreen: AppScreens("confirm_email_screen")
    object ForgottenPasswordScreen: AppScreens("forgotten_password_screen")
    object HomeScreen: AppScreens("home_screen")
    object ChatScreen: AppScreens("chat_screen")
}