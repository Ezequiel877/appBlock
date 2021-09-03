package com.example.blogapp.data.model

import com.google.firebase.Timestamp

data class dataIn(
    val email: String = "",
    val id: String = "",
    val postTime: String = "",
    val post_imagen: String = "",
    val time:Timestamp?=null
)

data class user(val email: String="", val username: String="" , val photo: String="")
