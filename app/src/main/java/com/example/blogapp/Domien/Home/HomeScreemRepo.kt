package com.example.blogapp.Domien.Home

import com.example.blogapp.IU.home.home.adapter.Result
import com.example.blogapp.data.model.dataIn

interface HomeScreemRepo {
    suspend fun getLetterPost(): Result<List<dataIn>>

}