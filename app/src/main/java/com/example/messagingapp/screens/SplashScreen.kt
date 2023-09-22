package com.example.messagingapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.messagingapp.R

@Preview
@Composable
fun SplashScreen(){
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painterResource(id = R.drawable.dice_chat_logo),
            contentDescription = "Splash screen logo with text",
            Modifier.fillMaxWidth().padding(horizontal = 12.dp)
        )
    }
}