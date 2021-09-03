package com.example.blogapp.Domien.Home

import android.graphics.Bitmap
import com.example.blogapp.data.model.Remote.repoLogin

class upPost(private val repo:repoLogin):upPots {
    override suspend fun getsPost(datos: Bitmap, descripcion: String)=repo.getPost(datos, descripcion)
}