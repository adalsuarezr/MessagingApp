package com.example.messagingapp.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.messagingapp.data.UserRegister
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor() : FirebaseRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()

    private val dbRegister = Firebase
        .database("https://messagingapp-10988-default-rtdb.europe-west1.firebasedatabase.app/")
        .getReference("register")
    private val dbChats = Firebase
        .database("https://messagingapp-10988-default-rtdb.europe-west1.firebasedatabase.app/")
        .getReference("register")

    private val _isEmailVerified = MutableLiveData<Boolean>(FirebaseAuth.getInstance().currentUser?.isEmailVerified!!)
    val isEmailVerified: LiveData<Boolean> = _isEmailVerified

    fun createUserEmailPassword(
        email: String,
        password: String,
        callback: (Boolean) -> Unit
    ) {
        firebaseAuth
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    if (!FirebaseAuth.getInstance().currentUser?.isEmailVerified!!) {
                        sendVerificationEmail()
                        _isEmailVerified.value= FirebaseAuth.getInstance().currentUser?.isEmailVerified
                        createUserRegister(email)
                        callback(true)
                    }
                } else {
                    callback(false)
                }
            }
    }

    fun sendVerificationEmail() {
        firebaseAuth.currentUser?.sendEmailVerification()
    }

    fun loginUserEmailPassword(
        email: String,
        password: String,
        callback: (Boolean) -> Unit
    ) {
        firebaseAuth
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    if (checkIfEmailVerified()) {
                        callback(true)
                    } else {
                        sendVerificationEmail()
                        callback(true)
                    }
                } else {
                    callback(false)
                }
            }
    }

    fun checkIfEmailVerified(): Boolean {
        _isEmailVerified.value = FirebaseAuth.getInstance().currentUser?.isEmailVerified!!
        return FirebaseAuth.getInstance().currentUser?.isEmailVerified!!
    }

    fun createUserRegister(email: String) {
        val userRegister = UserRegister(email.hashCode().toString())
        dbRegister.child(email.hashCode().toString()).setValue(userRegister)
    }
}