package com.example.messagingapp.data

data class UserRegister(
    var userId:String?=null,
    var chatsListIds: MutableList<String> = mutableListOf()
){
}
