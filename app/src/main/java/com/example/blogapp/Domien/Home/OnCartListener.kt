package com.example.blogapp.Domien.Home

import com.example.blogapp.data.model.Orden


interface OnCartListener {
    fun setQuanty(produc:Orden)
    fun showTotal(total:Int)



}