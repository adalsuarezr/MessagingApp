package com.example.messagingapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.messagingapp.R
import com.example.messagingapp.navigation.AppScreens
import com.example.messagingapp.screens.composables.MyCircularLoadingIndicator
import com.example.messagingapp.screens.composables.MyTextLink


@Composable
fun VerifyAccountScreen(navController: NavHostController) {
    //Check if the email is verified
    Column(Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Verify your email",
            modifier= Modifier.padding(12.dp),
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize =24.sp)
        Text("We have sent you a verification email. Please, check your email to continue." ,
            modifier= Modifier.padding(vertical=12.dp, horizontal=24.dp),
            textAlign = TextAlign.Center)
        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical=12.dp, horizontal=24.dp),
            horizontalArrangement = Arrangement.Center){
            Text(text = stringResource(id = R.string.go_back_to))
            MyTextLink(
                text = stringResource(id = R.string.login),
                Modifier.padding(start = 4.dp),
                navController,
                AppScreens.LoginScreen.route)
        }
        MyCircularLoadingIndicator(
            modifier = Modifier
                .padding(top = 48.dp)
                .fillMaxWidth(0.5f),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = 10.dp)
    }

}