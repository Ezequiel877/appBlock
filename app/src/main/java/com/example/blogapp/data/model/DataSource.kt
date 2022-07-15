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

data class Comercios(val email: String = "", val contraseña:String="", val id: String = "", val photo: String = "", val direccioo:String="", val horarios:String="")

data class Producto(@get:com.google.firebase.firestore.Exclude var id:String="", var nombre:String="", var descripcion:String="", val precio:String=""

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Producto

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}


data class Orden(@get:com.google.firebase.firestore.Exclude var id:String="",var name:String="", val product:Map<String, ProductoOrden> = hashMapOf(), val totalPrice:Int=0, val status:Int=0



) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Orden

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

}
data class ProductoOrden(@get:com.google.firebase.firestore.Exclude var id:String="",var name:String="", val cantidad:Int=0)