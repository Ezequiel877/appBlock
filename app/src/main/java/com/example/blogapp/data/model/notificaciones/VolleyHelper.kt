package com.example.kampasmobil2.Volley

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class VolleyHelper(context: Context) {

    companion object {
        @Volatile
        private var INSTANCE: VolleyHelper? = null
        fun getInstance(context: Context) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: VolleyHelper(context).also { INSTANCE = it }

        }
    }

    val requestK: RequestQueue by lazy { Volley.newRequestQueue(context.applicationContext) }


    fun <T> addtoRequest(req:Request<T>){
        requestK.add(req)
    }
}