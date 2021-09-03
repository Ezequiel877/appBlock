package com.example.blogapp.Domien.Home

import android.graphics.Bitmap

interface upPots {
    suspend fun getsPost(datos: Bitmap, descripcion:String)

}