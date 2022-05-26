package com.example.blogapp.Domien.Home

import com.example.blogapp.IU.home.home.adapter.Result
import com.example.blogapp.data.model.Remote.DataHomeRepo
import com.example.blogapp.data.model.DataSource
import com.example.blogapp.data.model.Producto

class HomeScreemImp(private var dataRepo: DataHomeRepo): HomeScreemRepo {
    override suspend fun getLetterPost(id :String): Result<List<Producto>> = dataRepo.getLeterPost(id)

}