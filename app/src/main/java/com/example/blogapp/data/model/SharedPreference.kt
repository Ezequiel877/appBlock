package com.example.blogapp.data.model

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson

class SharedPreference(contex: Context) {

    private var pref: SharedPreferences?=null

    init {
        pref=contex.getSharedPreferences("com.example.kampasmobil2.Core.Utils", Context.MODE_PRIVATE)

    }

    fun save(key:String, objeto: Any){

        try {
            var json= Gson()
            var gson=json.toJson(objeto)
            with(pref?.edit()){
                this?.putString(key, gson)
                this?.commit()
            }

        }catch (e:Exception){
            Log.d("ERRORDELPREFERENCE", "save: ERRORDESHARED"  +  "${e.message}")

        }

    }
    fun getData(key: String): String?{
        val data =pref?.getString(key, "")
        return data

    }

}