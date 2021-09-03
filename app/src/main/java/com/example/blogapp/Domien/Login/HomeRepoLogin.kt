package com.example.blogapp.Domien.Login

import android.graphics.Bitmap
import com.google.firebase.auth.FirebaseUser

interface HomeRepoLogin {

    suspend fun login(email:String,passwork:String): FirebaseUser?
    suspend fun longIN(email:String,passwork:String, username:String):FirebaseUser?
    suspend fun getPhoto(bitmap:Bitmap, name:String)

}