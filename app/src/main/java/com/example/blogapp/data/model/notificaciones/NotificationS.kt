package com.example.kampasmobil2.Volley

import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.blogapp.data.model.constantes
import org.json.JSONException
import org.json.JSONObject
import kotlin.jvm.Throws

class NotificationS {

    fun sendNotication(title: String, messege: String, token: String) {
        val json=JSONObject()
        json.put(constantes.Method_, constantes.SEND_NOTIF)
        json.put(constantes.title_, title)
        json.put(constantes.message, messege)
        json.put(constantes.token, token)
        val jsonObject:JsonObjectRequest= object :JsonObjectRequest(Method.POST,constantes.APPWEB, json, Response.Listener { response->
            try {
                val succes=response.getInt(constantes.Success)
                Log.d("NOTIFVOLLEY", "sendNotication: ${succes.toString()} ")
                Log.d("NOTIFVOLLEY", "sendNotication: ${response.toString()} ")
            }catch (e:JSONException){
                e.printStackTrace()
                Log.e("NOTIFVOLLEY", "sendNotication: ${e.localizedMessage}")
            }
        }, Response.ErrorListener {error->
            if (error.localizedMessage != null){
                Log.e("NOTIFVOLLEY", "sendNotication: ${error.localizedMessage}")
            }
        }){
            @Throws(AuthFailureError::class)
            override fun getHeaders(): MutableMap<String, String> {
                val headers=HashMap<String, String>()
                headers["Content-Type"]="application/json; charset=utf-8"
                return super.getHeaders()
            }
        }
        MarketIt.volleyHelper.addtoRequest(jsonObject)
    }
}