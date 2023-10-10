package com.example.messagingapp.screens.composables

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.messagingapp.viewmodels.AuthetificationViewModel

@Composable
fun MyAccessButton(
    text:String,
    viewModel: AuthetificationViewModel,
    onClick: () -> Unit,
    enabled: Boolean,
    modifier:Modifier,
    navController: NavHostController){
    TextButton(
        onClick = {
            onClick
        },
        modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.secondary),
        enabled = enabled
    ) {
        Text(text=text, color= Color.White, fontWeight = FontWeight.Bold)
    }
}