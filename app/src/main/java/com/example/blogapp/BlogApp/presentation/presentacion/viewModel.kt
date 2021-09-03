package com.example.blogapp.BlogApp.presentation.presentacion

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.blogapp.Domien.Home.upPots
import com.example.blogapp.Domien.Login.HomeRepoLogin
import com.example.blogapp.IU.home.home.adapter.Result
import kotlinx.coroutines.Dispatchers

class viewModel(private val repo:upPots):ViewModel() {
    fun getPhoto(imagenBitmap: Bitmap, name:String)= liveData(Dispatchers.IO) {
        emit(Result.Loading())
        try {
            emit(Result.Succes(repo.getsPost(imagenBitmap, name)))

        }catch (e:Exception){
            emit(Result.Failure(e))
        }
    }
}

class factoryImp(private val repo: upPots) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(dataIn: Class<T>): T {
        return viewModel(repo) as T
}
}


