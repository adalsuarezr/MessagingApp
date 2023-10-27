package com.example.messagingapp.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.messagingapp.R
import com.example.messagingapp.domain.FirebaseRepositoryImpl
import com.example.messagingapp.navigation.AppScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(private val repository: FirebaseRepositoryImpl): ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _repeatPassword = MutableLiveData<String>()
    val repeatPassword: LiveData<String> = _repeatPassword

    private val _isEmailVerified = MutableLiveData(false)
    private val isEmailVerified: LiveData<Boolean> = _isEmailVerified

    init {
        repository.isEmailVerified.observeForever{ _isEmailVerified.value=it }

    }

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

    fun emailErrorMessage(context:Context, email: String): String {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
        return when {
            !email.matches(emailRegex.toRegex()) && email.isNotBlank() ->
                context.getString(R.string.email_error_format)
            else -> ""
        }
    }
    fun passwordErrorMessage(context:Context, password:String):String {

        return when {
            password.length<8 && password.isNotBlank() ->
                context.getString(R.string.password_error_too_short)
            password.length>20 ->
                context.getString(R.string.password_error_too_long)
            //No numbers
            password.matches(("^[^0-9]*$").toRegex()) && password.isNotBlank() ->
                context.getString(R.string.password_error_no_numbers)
            //No letters
            password.matches(("^[^a-zA-Z]*$").toRegex()) && password.isNotBlank()->
                context.getString(R.string.password_error_no_characters)
            else->""
        }
    }
    fun repeatPasswordErrorMessage(context:Context, repeatPassword:String):String{
        return if(repeatPassword!=password.value.toString() && repeatPassword.isNotBlank()){
            context.getString(R.string.repeat_password_error_match)
        }else{
            ""
        }
    }

    fun enableLogin(): Boolean {
        return emailValidator(email.value.toString()) && passwordValidator(password.value.toString())
    }

    fun enableSignUp(): Boolean {
        return emailValidator(email.value.toString()) && passwordValidator(password.value.toString()) && repeatPasswordValidator(repeatPassword.value.toString())
    }

    fun createAccountEmailPassword(context: Context, navController: NavController) {
        repository.createUserEmailPassword(_email.value.toString(), _password.value.toString()) { success, errorMessage ->
            if (success) {
                navController.popBackStack()
                navController.navigate(AppScreens.VerifyAccountScreen.route)
                repository.sendVerificationEmail()
                repository.updateEmailVerified()
            } else {
                makeToast(context, "Failed to create account: $errorMessage", Toast.LENGTH_LONG)
            }
        }
    }

    fun loginUserEmailPassword(context: Context, navController: NavController){
        repository.loginUserEmailPassword(_email.value.toString(),_password.value.toString()){success, errorMessage->
            if(success){
                if(isEmailVerified.value==true){
                    navController.popBackStack()
                    navController.navigate(AppScreens.HomeScreen.route)
                }else if(isEmailVerified.value==false){
                    repository.sendVerificationEmail()
                    repository.updateEmailVerified()
                    navController.popBackStack()
                    navController.navigate(AppScreens.VerifyAccountScreen.route)
                }
            } else {
                makeToast(context, "Failed to login: $errorMessage", Toast.LENGTH_LONG)
            }
        }
    }


    private fun makeToast(context: Context, message: String, length: Int){
        Toast.makeText(context,message,length).show()
    }

    fun sendPasswordReset(context: Context, navController: NavController){
        repository.sendPasswordResetEmail(email.value.toString()){success, errorMessage ->
            if (success) {
                if(repository.getEmailVerified()){
                    navController.popBackStack()
                    navController.navigate(AppScreens.HomeScreen.route)
                } else{
                navController.popBackStack()
                navController.navigate(AppScreens.ForgottenPasswordScreen.route)
                }
            } else {
                makeToast(context, "Failed to send reset password email: $errorMessage", Toast.LENGTH_LONG)
            }
        }
    }

    fun onForgottenPasswordClicked(){
        repository.sendVerificationEmail()
        repository.updateEmailVerified()
    }


}