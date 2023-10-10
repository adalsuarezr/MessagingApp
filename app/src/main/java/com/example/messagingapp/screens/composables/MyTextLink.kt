package com.example.messagingapp.screens.composables

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController

@Composable
fun MyTextLink(text: String, modifier: Modifier, navController: NavHostController, route: String,) {
    Text(
        text=text,
        modifier = modifier
            .clickable {
                navController.popBackStack()
                navController.navigate(route) },
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold)
}