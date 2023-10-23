package com.example.messagingapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MarkEmailRead
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.messagingapp.R
import com.example.messagingapp.screens.composables.MyAccessButton
import com.example.messagingapp.screens.composables.MyTextField
import com.example.messagingapp.viewmodels.AuthetificationViewModel

@Composable
fun ForgottenPasswordScreen(navController: NavHostController, viewModel: AuthetificationViewModel) {
    val focusManager = LocalFocusManager.current
    val email: String by viewModel.email.observeAsState(initial = "")

    val context = LocalContext.current

    Column(
        Modifier.fillMaxSize().padding(top=24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Screen label
        Text(
            stringResource(id = R.string.forgot_password).uppercase(),
            Modifier.padding(vertical = 12.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            stringResource(id = R.string.forgot_password).uppercase(),
            Modifier.padding(vertical = 12.dp),
            fontWeight = FontWeight.Bold,
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

        //LoginButton. Access to Home
        MyAccessButton(
            text = stringResource(id = R.string.reset_password),
            onClick = {
                viewModel.sendPasswordReset(context, navController)
            },
            enabled= viewModel.emailValidator(email),
            Modifier
                .fillMaxWidth(0.70f)
                .padding(vertical = 12.dp)
        )
    }
}