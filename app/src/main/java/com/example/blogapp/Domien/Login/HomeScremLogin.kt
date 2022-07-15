package com.example.blogapp.Domien.Login

import android.graphics.Bitmap
import com.example.blogapp.data.model.Comercios
import com.example.blogapp.data.model.Remote.repoLogin
import com.google.firebase.auth.FirebaseUser

 class homeScremLogin(private val data:repoLogin): HomeRepoLogin {
    override suspend fun login(email: String, passwork: String): FirebaseUser? =data.singIn(email, passwork)
    override suspend fun longIN(nombre:String,email: String, passwork: String): FirebaseUser? =data.singUp(nombre, email, passwork)
    override suspend fun getPhoto(bitmap: Bitmap, name: String) =data.getPhoto(bitmap, name)

 }
