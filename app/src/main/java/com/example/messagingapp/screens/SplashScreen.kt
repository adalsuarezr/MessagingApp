package com.example.messagingapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.example.messagingapp.viewmodels.AuthenticationViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController, authVM: AuthenticationViewModel){
    LaunchedEffect(key1 = true){    //Todo -- Initialization task should be called here
        delay(1000)
        navController.popBackStack()
        navController.navigate(AppScreens.LoginScreen.route)
    }

    Splash()
}
@Preview
@Composable
fun Splash() {
    Box(modifier=Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painterResource(id = R.drawable.dice_chat_logo),
            contentDescription = "Splash screen logo with text",
            Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        )
        Image(
            painterResource(id = R.drawable.by_addev),
            contentDescription = "dev signature",
            Modifier
                .width(136.dp)
                .padding(12.dp)
                .align(alignment = Alignment.BottomEnd),
            Alignment.BottomEnd
        )
    }
}
