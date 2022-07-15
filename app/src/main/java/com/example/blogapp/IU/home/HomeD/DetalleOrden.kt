package com.example.blogapp.IU.home.HomeD

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.blogapp.R
import com.example.blogapp.databinding.FragmentDetalleOrdenBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson


class DetalleOrden : Fragment(R.layout.fragment_detalle_orden) {

    private lateinit var binding: FragmentDetalleOrdenBinding
    var selectProducto = ArrayList<OrdenStar>()
    var json = Gson()
    private val total = 0.0
    private val id = ""
    private var token = ""
    private var ordendesc = ""
    private var doblw = 0
    private var nametoken = ""


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetalleOrdenBinding.bind(view)

        val user = FirebaseFirestore.getInstance().collection("comercios").document("args.idcommers")
        val tokenEcommers = user.collection("token").get().addOnSuccessListener { document ->
            for (d in document) {

                val tokendelServer = d.data
                token += "${tokendelServer.getValue("token")},"
                //contraseña citio web;Gvy4&v6qLciM)D6WMKU^
            }
            if (token.length > 0) token = token.dropLast(1)

        }


    }
}