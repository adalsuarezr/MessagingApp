package com.example.messagingapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthetificationViewModel: ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _repeatPassword = MutableLiveData<String>()
    val repeatPassword: LiveData<String> = _repeatPassword

    fun onEmailChanged(email: String) {
        _email.value = email
    }

    fun onPasswordChanged(password: String) {
        _password.value = password
    }

    fun onRepeatPasswordChanged(repeatPassword: String) {
        _repeatPassword.value = repeatPassword
    }

    fun emailValidator(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
        return email.matches(emailRegex.toRegex())
    }

    fun passwordValidator(password: String): Boolean {
        val passwordRegex = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,19}$"
        return password.matches(passwordRegex.toRegex())
    }

    fun repeatPasswordValidator(repeatPassword: String) = repeatPassword==password.value.toString()

    fun emailErrorMessage(email: String): String {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
        return when {
            !email.matches(emailRegex.toRegex()) -> "Invalid email format"
            else -> ""
        }
    }
    fun passwordErrorMessage(password:String):String {

        return when {
            password.length<8 -> "Password too short"
            password.length>20 -> "Password too long"
            //No numbers
            password.matches(("^[^0-9]*$").toRegex())->"Include at least a number"
            //No letters
            password.matches(("^[^a-zA-Z]*$").toRegex())->"Include at least a letter"
            else->""
        }
    }
    fun repeatPasswordErrorMessage(repeatPassword:String):String{
        return if(repeatPassword!=password.value.toString()){
            "Passwords are not equal"
        }else{
            ""
        }
    }
}