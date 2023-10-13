package com.example.messagingapp.domain

interface FirebaseRepository {
    fun createUserEmailPassword(email: String, password: String, callback: (Boolean) -> Unit)
    fun createUserRegister(email: String)
}