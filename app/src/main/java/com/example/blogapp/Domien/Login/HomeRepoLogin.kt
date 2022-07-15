package com.example.blogapp.Domien.Login

import android.graphics.Bitmap
import com.example.blogapp.data.model.Comercios
import com.google.firebase.auth.FirebaseUser

interface HomeRepoLogin {

    suspend fun login(email:String,passwork:String): FirebaseUser?
    suspend fun longIN(nombre:String,email: String, passwork: String):FirebaseUser?
    suspend fun getPhoto(bitmap:Bitmap, name:String)

}