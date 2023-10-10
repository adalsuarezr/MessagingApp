package com.example.messagingapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MarkEmailRead
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.messagingapp.R
import com.example.messagingapp.navigation.AppScreens
import com.example.messagingapp.screens.composables.MyAccessButton
import com.example.messagingapp.screens.composables.MyTextField
import com.example.messagingapp.screens.composables.MyTextLink
import com.example.messagingapp.viewmodels.AuthetificationViewModel

@Composable
fun LoginScreen(navController: NavHostController, viewModel: AuthetificationViewModel) {
    val focusManager = LocalFocusManager.current
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val context = LocalContext.current
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Screen label
        Text(
            stringResource(id = R.string.login).uppercase(),
            Modifier.padding(vertical = 12.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = MaterialTheme.colorScheme.primary
        )
        //Email TextField
        MyTextField(
            email,
            { Text(stringResource(id = R.string.email)) },
            { Text(stringResource(id = R.string.email_placeholder)) },
            Modifier
                .fillMaxWidth(0.80f)
                .padding(vertical = 12.dp),
            Icons.Filled.MarkEmailRead,
            Icons.Filled.Email,
            KeyboardType.Email,
            ImeAction.Next,
            FocusDirection.Down,
            viewModel,
            focusManager,
            onTextChanged = {updatedText->viewModel.onEmailChanged(updatedText) },
            validator = { viewModel.emailValidator(email) },
            errorMessage = {viewModel.emailErrorMessage(context, email)}
        )

        //Password TextField
        MyTextField(
            password,
            { Text(stringResource(id = R.string.password)) },
            { Text(stringResource(id = R.string.password_placeholder)) },
            Modifier
                .fillMaxWidth(0.80f)
                .padding(vertical = 12.dp),
            Icons.Filled.VisibilityOff,
            Icons.Filled.Visibility,
            KeyboardType.Password,
            ImeAction.Next,
            FocusDirection.Down,
            viewModel,
            focusManager,
            onTextChanged = {password->viewModel.onPasswordChanged(password) },
            validator = { viewModel.passwordValidator(password) },
            errorMessage = { viewModel.passwordErrorMessage(context,password) }
        )

        //Dont have account plus link to access SignUpScreen
        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.Center){
            Text(text = stringResource(id = R.string.dont_have_account))
            MyTextLink(
                text = stringResource(id = R.string.sign_up),
                Modifier.padding(start = 4.dp),
                navController,
                AppScreens.SignUpScreen.route)
        }
        
        
        //LoginButton. Access to Home
        MyAccessButton(
            text = stringResource(id = R.string.login),
            viewModel = viewModel,
            onClick = {
                navController.popBackStack()
                navController.navigate(AppScreens.HomeScreen.route)
            },
            enabled=
                viewModel.enableLogin()
            ,
            Modifier
                .fillMaxWidth(0.70f)
                .padding(vertical = 12.dp),
            navController = navController)

        //Dividers plus or
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .wrapContentHeight()
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 12.dp)
        ) {
            Divider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = stringResource(id = R.string.or),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 0.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center

            )
            Divider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically))
        }

        //Google, Facebook and X buttons
        Row (Modifier.fillMaxWidth().padding(vertical=12.dp, horizontal = 24.dp), horizontalArrangement = Arrangement.SpaceAround){
            Image(
                painterResource(id = R.drawable.google_192),
                contentDescription = "dev signature",
                Modifier
                    .clickable {  }
                    .size(64.dp)
                    .weight(1f)
                    .padding(start = 0.dp)

            )
            Image(
                painterResource(id = R.drawable.facebook_192),
                contentDescription = "dev signature",
                Modifier
                    .clickable {  }
                    .size(64.dp)
                    .weight(1f)

            )
            Image(
                painterResource(id = R.drawable.x_96),
                contentDescription = "dev signature",
                Modifier
                    .clickable { }
                    .size(64.dp)
                    .weight(1f)
                    .padding(end = 0.dp)
            )

        }

    }
}

/*
        //RepeatPassword TextField
        MyTextField(
            repeatPassword,
            { Text(stringResource(id = R.string.repeat_password)) },
            { Text(stringResource(id = R.string.repeat_password_placeholder)) },
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp),
            Icons.Filled.Visibility,
            Icons.Filled.VisibilityOff,
            KeyboardType.Password,
            ImeAction.Next,
            FocusDirection.Down,
            viewModel,
            focusManager,
            onTextChanged = {repeatPassword -> viewModel.onRepeatPasswordChanged(repeatPassword) },
            validator = { viewModel.repeatPasswordValidator(password) },
            errorMessage = { viewModel.repeatPasswordErrorMessage(password) }
        )*/
