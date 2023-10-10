package com.example.messagingapp.screens.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.messagingapp.viewmodels.AuthetificationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(
    value: String,
    label: @Composable () -> Unit,
    placeholder: @Composable () -> Unit,
    modifier: Modifier,
    icon1: ImageVector,
    icon2: ImageVector,
    keyboardType: KeyboardType,
    imeActions: ImeAction,
    focusDirection: FocusDirection,
    viewModel: AuthetificationViewModel,
    focusManager: FocusManager,
    onTextChanged: (String) -> Unit,
    validator: () -> Boolean,
    errorMessage: () -> String?
) {
    val trailingIconVisibility = rememberSaveable { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    var focusColor by remember { mutableStateOf(Color.Gray) }
    val focusColorDefault = MaterialTheme.colorScheme.primary
    val error = errorMessage()
    OutlinedTextField(

        value = value,
        onValueChange = {updatedText -> onTextChanged(updatedText)},
        modifier = Modifier
            .focusRequester(focusRequester)
            .onFocusChanged {
                focusColor = if (it.isFocused) focusColorDefault else Color.Gray
            },
        enabled = true,
        readOnly = false,
        textStyle = LocalTextStyle.current,
        label = label,
        placeholder=placeholder,
        shape = RoundedCornerShape(24.dp),
        leadingIcon = {
            if (trailingIconVisibility.value) {
                Icon(
                    icon1,
                    contentDescription = null,
                    Modifier.clickable {
                        trailingIconVisibility.value = !trailingIconVisibility.value
                    },
                    tint = focusColor
                )
            }else{
                Icon(
                    icon2,
                    contentDescription = null,
                    Modifier.clickable {
                        trailingIconVisibility.value = !trailingIconVisibility.value
                    },
                    tint = focusColor
                )
            }
        },
        isError= !validator() && value.isNotBlank(),
        supportingText = {
            if(error!=null){
                Text(text = error.toString())
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
        colors = TextFieldDefaults.textFieldColors(
            focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
            containerColor = Color.White,
            placeholderColor = Color.Gray,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = Color.Gray,
            unfocusedIndicatorColor = Color.Gray,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            textColor = focusColor
        )
    )
}