package com.example.blogapp.data.model.Remote

import android.os.Parcel
import android.os.Parcelable
import com.example.blogapp.IU.home.home.adapter.Result
import com.example.blogapp.data.model.DataSource
import com.example.blogapp.data.model.Producto
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class DataHomeRepo() {

    suspend fun getLeterPost(id: String): Result<List<Producto>> {
        val lista = mutableListOf<Producto>()
        val quarySnat = FirebaseFirestore.getInstance().collection("comercios").document(id)
        val it = quarySnat.collection("productos").get().await()
        for (post in it.documents) {

           val producto= post.toObject(Producto::class.java)
                producto!!.id ==post.id
                lista.add(producto)

        }
        return Result.Succes(lista)
    }

    suspend fun getOrdenes(id: String): Result<List<Producto>> {
        val lista = mutableListOf<Producto>()
        val quarySnat = FirebaseFirestore.getInstance().collection("comercios").document(id)
        val it = quarySnat.collection("ordenes").get().await()
        for (post in it.documents) {

            post.toObject(Producto::class.java)?.let { lista.add(it) }
        }
        return Result.Succes(lista)
    }

}