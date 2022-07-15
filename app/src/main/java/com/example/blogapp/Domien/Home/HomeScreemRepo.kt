package com.example.blogapp.Domien.Home

import com.example.blogapp.IU.home.home.adapter.Result
import com.example.blogapp.data.model.DataSource
import com.example.blogapp.data.model.Producto

interface HomeScreemRepo {
    suspend fun getLetterPost(id :String): Result<List<Producto>>
    suspend fun getOrdenes(id :String): Result<List<Producto>>

}