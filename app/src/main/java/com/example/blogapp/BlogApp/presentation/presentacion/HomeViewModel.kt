package com.example.blogapp.BlogApp.presentation.presentacion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.blogapp.Domien.Home.HomeScreemRepo
import com.example.blogapp.IU.home.home.adapter.Result
import kotlinx.coroutines.Dispatchers

class HomeViewModel(private var home: HomeScreemRepo) : ViewModel() {
    fun getLetterPost() = liveData(Dispatchers.IO) {
        emit(Result.Loading())
        try {
            emit(home.getLetterPost())

        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }
}



    class HomeScreemFactotry(private val home: HomeScreemRepo) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(dataIn: Class<T>): T {
            return dataIn.getConstructor(HomeScreemRepo::class.java).newInstance(home)
        }
    }




