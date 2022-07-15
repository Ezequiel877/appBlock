package com.example.blogapp.IU.home.HomeD

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blogapp.Domien.Home.OrdenInterface
import com.example.blogapp.IU.home.home.adapter.OrdenAdapter
import com.example.blogapp.R
import com.example.blogapp.data.model.Comercios
import com.example.blogapp.data.model.Orden
import com.example.blogapp.data.model.SharedPreference
import com.example.blogapp.data.model.notificaciones.UsesProvider
import com.example.blogapp.databinding.FragmentBlankHomeBinding
import com.example.blogapp.databinding.FragmentOrdenStarBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class OrdenStar : Fragment(R.layout.fragment_orden_star), OrdenInterface {
    private lateinit var binding: FragmentOrdenStarBinding
    private lateinit var adapter:OrdenAdapter
    private var id: String = ""
    var sharePref: SharedPreference? = null
    var selectProducto = ArrayList<Comercios>()
    var product: Comercios? = null
    var json = Gson()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentOrdenStarBinding.bind(view)
        recycler()
        setupRecycler()
        getOrden()
        Log.d("tagdelrepodato", "recycler: $id")

    }

    private fun setupRecycler() {
        adapter= OrdenAdapter(mutableListOf(), this)
        binding.recyclerView.apply {
            layoutManager=LinearLayoutManager(context)
            adapter=this@OrdenStar.adapter
        }
    }
    private fun recycler() {
        sharePref = SharedPreference(requireContext())
        var doblw = 0
        if (!sharePref?.getData("id").isNullOrBlank()) {
            val type = object : TypeToken<ArrayList<Comercios>>() {}.type
            selectProducto = json.fromJson(sharePref?.getData("id"), type)
            id = selectProducto[0].id
            Log.d("tagdelrepodato", "recycler: $id")

        }
    }
    /**/
    private fun getOrden() {
        Log.d("tagdelrepodato", "recycler: $id")

        val firebase=FirebaseFirestore.getInstance().collection("comercios").document(id)
        firebase.collection("ordenes").get().addOnSuccessListener {
            for (d in it){
                val orden=d.toObject(Orden::class.java)
                orden.name=d.id
                challengeStatus(orden)
                adapter.add(orden)
            }
        }.addOnFailureListener {
            Toast.makeText(context, "fallo al leer ordenes", Toast.LENGTH_SHORT).show()
        }
    }

    private fun challengeStatus(orden: Orden) {
        val navegacion=OrdenStarDirections.actionOrdenStarToDetalleOrden(orden.id)
        findNavController().navigate(navegacion)
    }

    override fun orden(orden: Orden) {
        TODO("Not yet implemented")
    }

    override fun onChatOrnde(orden: Orden) {
        TODO("Not yet implemented")
    }


}