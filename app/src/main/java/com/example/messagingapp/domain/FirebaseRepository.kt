package com.example.messagingapp.domain

interface FirebaseRepository {
    fun sendVerificationEmail()
    fun sendPasswordResetEmail(email: String, callback: (Boolean, String?) -> Unit)
    fun getEmailVerified(): Boolean
    fun updateEmailVerified()
    fun createUserRegister(email: String)
    fun createUserEmailPassword(
        email: String,
        password: String,
        callback: (Boolean, String?) -> Unit
    )
    fun loginUserEmailPassword(
        email: String,
        password: String,
        callback: (Boolean, String?) -> Unit)
}