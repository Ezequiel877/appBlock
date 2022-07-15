package com.example.kampasmobil2.Volley

import android.app.Application

class MarketIt:Application() {

    companion object{
        lateinit var volleyHelper: VolleyHelper
    }

    override fun onCreate() {
        super.onCreate()
        volleyHelper= VolleyHelper.getInstance(this)
    }
}