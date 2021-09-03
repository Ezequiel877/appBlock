package com.example.blogapp.data.model.Remote

import com.example.blogapp.IU.home.home.adapter.Result
import com.example.blogapp.data.model.dataIn
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class DataHomeRepo {
    suspend fun getLeterPost(): Result<List<dataIn>> {
        val lista = mutableListOf<dataIn>()
        val quarySnat = FirebaseFirestore.getInstance().collection("post").get().await()
        for (post in quarySnat.documents) {
            post.toObject(dataIn::class.java)?.let { lista.add(it) }
        }
        return Result.Succes(lista)
    }}