package com.example.messagingapp.domain

import com.example.messagingapp.data.UserRegister
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseRepositoryImpl: FirebaseRepository {
    val dbRegister= Firebase
        .database("https://messagingapp-10988-default-rtdb.europe-west1.firebasedatabase.app/")
        .getReference("register")
    val dbChats= Firebase
        .database("https://messagingapp-10988-default-rtdb.europe-west1.firebasedatabase.app/")
        .getReference("register")

    override fun createUserEmailPassword(
        email: String,
        password: String,
        callback: (Boolean) -> Unit
    ) {
        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    if (!FirebaseAuth.getInstance().currentUser?.isEmailVerified!!) {
                        FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
                        createUserRegister(email)
                        callback(true)
                    }
                } else {
                    callback(false)
                    FirebaseAuth.getInstance().signOut()
                }
            }
    }

    override fun createUserRegister(email: String) {
        val userRegister = UserRegister(email.hashCode().toString())
        dbRegister.child(email.hashCode().toString()).setValue(userRegister)
    }


}