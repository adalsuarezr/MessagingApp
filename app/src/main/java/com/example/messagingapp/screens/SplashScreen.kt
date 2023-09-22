package com.example.messagingapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.messagingapp.R
import com.example.messagingapp.navigation.AppScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController){
    LaunchedEffect(key1 = true){    //Todo -- Initialization task should be called here
        delay(1000)
        navController.popBackStack()
        navController.navigate(AppScreens.HomeScreen.route)
    }

    Splash()
}
@Preview
@Composable
fun Splash() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painterResource(id = R.drawable.dice_chat_logo),
            contentDescription = "Splash screen logo with text",
            Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        )
    }
}
