package com.example.messagingapp.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.messagingapp.data.UserRegister
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FirebaseRepositoryImpl @Inject constructor() : FirebaseRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()

    private val dbRegister = Firebase
        .database("https://messagingapp-10988-default-rtdb.europe-west1.firebasedatabase.app/")
        .getReference("register")
    private val dbChats = Firebase
        .database("https://messagingapp-10988-default-rtdb.europe-west1.firebasedatabase.app/")
        .getReference("register")

    private val _isEmailVerified =
        MutableLiveData<Boolean>(FirebaseAuth.getInstance().currentUser?.isEmailVerified!!)
    val isEmailVerified: LiveData<Boolean> = _isEmailVerified
    override fun createUserEmailPassword(
        email: String,
        password: String,
        callback: (Boolean, String?) -> Unit // Update the callback to include an error message
    ) {
        firebaseAuth
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (!FirebaseAuth.getInstance().currentUser?.isEmailVerified!!) {
                        sendVerificationEmail()
                        _isEmailVerified.value =
                            FirebaseAuth.getInstance().currentUser?.isEmailVerified
                        createUserRegister(email)
                        callback(true, "success")
                    }
                }
            }
            .addOnFailureListener { exception ->
                //todo check why is always the else and put strings in strings.xml
                val errorMessage =
                    when (val errorCode = (exception as? FirebaseAuthException)?.errorCode) {
                        "ERROR_EMAIL_ALREADY_IN_USE" -> "The email address is already in use by another account."
                        "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL" -> "The email address is associated with another account."
                        else -> "An unknown error occurred: $errorCode"
                    }
                callback(false, errorMessage)
            }
    }

    override fun sendVerificationEmail() {
        firebaseAuth.currentUser?.sendEmailVerification()
    }

    override fun sendPasswordResetEmail(
        email: String,
        callback: (Boolean, String?) -> Unit
    ) {
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (checkIfEmailVerified()) {
                        callback(true, "success")
                    } else {
                        sendVerificationEmail()
                        callback(true, "success")
                    }
                }
            }.addOnFailureListener { exception ->
                val errorMessage =
                    when ((exception as? FirebaseAuthException)?.errorCode) {
                        "ERROR_USER_NOT_FOUND" -> "There is no user corresponding to the given email."
                        else -> "An unknown error occurred."
                    }
                callback(false, errorMessage)
            }
    }

    override fun loginUserEmailPassword(
        email: String,
        password: String,
        callback: (Boolean, String?) -> Unit // Update the callback to include an error message
    ) {
        firebaseAuth
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (checkIfEmailVerified()) {
                        callback(true, "success")
                    } else {
                        sendVerificationEmail()
                        callback(true, "success")
                    }
                }
            }
            .addOnFailureListener { exception ->
                val errorMessage =
                    when ((exception as? FirebaseAuthException)?.errorCode) {
                        //todo check why is always the else and put strings in strings.xml
                        "ERROR_WRONG_PASSWORD" -> "The password is invalid."
                        "ERROR_USER_NOT_FOUND" -> "There is no user corresponding to the given email."
                        "ERROR_EMAIL_ALREADY_IN_USE" -> "The email address is already in use by another account."
                        "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL" -> "The email address is associated with another account."
                        "ERROR_CREDENTIAL_ALREADY_IN_USE" -> "The credential is already in use."
                        else -> "An unknown error occurred."
                    }
                callback(false, errorMessage)
            }
    }

    override fun checkIfEmailVerified(): Boolean {
        _isEmailVerified.value = FirebaseAuth.getInstance().currentUser?.isEmailVerified!!
        return FirebaseAuth.getInstance().currentUser?.isEmailVerified!!
    }

    override fun createUserRegister(email: String) {
        val userRegister = UserRegister(email.hashCode().toString())
        dbRegister.child(email.hashCode().toString()).setValue(userRegister)
    }
}