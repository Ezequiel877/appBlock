package com.example.blogapp.BlogApp.presentation.presentacion

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.blogapp.Domien.Login.HomeRepoLogin
import com.example.blogapp.IU.home.home.adapter.Result
import com.example.blogapp.data.model.Comercios
import kotlinx.coroutines.Dispatchers

class viewModel2(private val login: HomeRepoLogin) : ViewModel() {
    fun singIn(email: String, passwork: String) = liveData(Dispatchers.IO) {
        emit(Result.Loading())
        try {
            emit(Result.Succes(login.login(email, passwork)))

        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }
        fun singUn(nombre:String,email: String, passwork: String) = liveData(Dispatchers.IO) {
            emit(Result.Loading())
            try {
                emit(Result.Succes(login.longIN(nombre, email, passwork)))

            } catch (e: Exception) {
                emit(Result.Failure(e))
            }
        }

    fun getPhoto(imagenBitmap: Bitmap, name:String)= liveData(Dispatchers.IO) {
        emit(Result.Loading())
        try {
            emit(Result.Succes(login.getPhoto(imagenBitmap, name)))

        }catch (e:Exception){
            emit(Result.Failure(e))
        }
    }
    }



    class factory(private val login: HomeRepoLogin) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(dataIn: Class<T>): T {
     return viewModel2(login) as T
        }
    }



