package com.example.blogapp.Domien.Home

import com.example.blogapp.IU.home.home.adapter.Result
import com.example.blogapp.data.model.Remote.DataHomeRepo
import com.example.blogapp.data.model.dataIn

class HomeScreemImp(private var dataRepo: DataHomeRepo): HomeScreemRepo {
    override suspend fun getLetterPost(): Result<List<dataIn>> = dataRepo.getLeterPost()

}