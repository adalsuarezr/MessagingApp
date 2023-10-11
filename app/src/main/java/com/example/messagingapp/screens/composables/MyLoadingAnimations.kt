package com.example.messagingapp.screens.composables

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun MyCircularLoadingIndicator(modifier:Modifier, color:Color, strokeWidth:Dp) {

    CircularProgressIndicator(
        modifier,
        color,
        strokeWidth)
}