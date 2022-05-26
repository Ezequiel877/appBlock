package com.example.blogapp.data.model

data class DataSource(
    var precios: Int = 0,
    @get:com.google.firebase.firestore.Exclude
    var precioProduc: Int = 0,
    val ImagenD: String = "",
    val imagen: String = "",
    val Cofertas: String = "",
    var detalles: String = "",
    @get:com.google.firebase.firestore.Exclude
    var precio: Int = 0,
    var nombre: String = "",
    val ubicacion: String = "",
    @get:com.google.firebase.firestore.Exclude
    var intCantidad: Int = 1,
    var product_Type: String = "",
    val picture: java.sql.Timestamp? = null,
)

data class user(val email: String = "", val username: String = "", val photo: String = "")
data class Producto(val name:String="", val descripcion:String="", val precio:Int=0,val cantidad:Int=0)