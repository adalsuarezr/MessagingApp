package com.example.messagingapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.messagingapp.screens.composables.MyTextField
import com.example.messagingapp.viewmodels.AuthetificationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController, viewModel: AuthetificationViewModel) {
    val focusManager = LocalFocusManager.current
    Column(Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Login")
        MyTextField(    //Todo no strings hardcoded
            "Email", "Email",
            "Email",
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp),
            Icons.Filled.Email,
            Icons.Filled.Email, //Todo Import extended icon
            KeyboardType.Email,
            ImeAction.Next,
            FocusDirection.Down,
            viewModel,
            focusManager,
            TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                placeholderColor = Color.Gray,
                unfocusedLabelColor = Color.Gray,
                focusedLabelColor = Color.Black,
                unfocusedIndicatorColor = Color.Black,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary
            )
        ) {}
    }
}
