package com.example.messagingapp.screens.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(
    myValue: String,
    label: String,
    placeholder: String,
    modifier: Modifier,
    icon1:ImageVector,
    icon2:ImageVector,
    keyboardType: KeyboardType,
    imeActions: ImeAction,
    focusDirection: FocusDirection,
    viewModel: ViewModel,
    focusManager: FocusManager,
    textFieldColors:TextFieldColors,
    onTextChanged: (String) -> Unit
) {
    val trailingIconVisibility = rememberSaveable { mutableStateOf(true) }

    OutlinedTextField(
        value = myValue,
        onValueChange = {},
        Modifier.wrapContentWidth(),
        label = {label},
        placeholder={placeholder},
        leadingIcon = {
            if (trailingIconVisibility.value) {
                Icon(
                    icon1, contentDescription = null,
                    Modifier.clickable {
                        trailingIconVisibility.value = !trailingIconVisibility.value
                    },
                    tint = Color.Gray
                )
            }else{
                Icon(
                    icon2, contentDescription = null,
                    Modifier.clickable {
                        trailingIconVisibility.value = !trailingIconVisibility.value
                    },
                    tint = Color.Gray
                )
            }
        },
        visualTransformation = if (!trailingIconVisibility.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeActions),
        keyboardActions = KeyboardActions(onNext = {focusManager.moveFocus(focusDirection)}),
        colors = textFieldColors
    )
}