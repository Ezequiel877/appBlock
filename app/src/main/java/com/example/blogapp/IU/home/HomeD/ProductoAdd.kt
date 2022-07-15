package com.example.blogapp.IU.home.HomeD

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.blogapp.BlogApp.presentation.presentacion.HomeScreemFactotry
import com.example.blogapp.BlogApp.presentation.presentacion.HomeViewModel
import com.example.blogapp.Card.AddProductoDialogArgs
import com.example.blogapp.Domien.Home.HomeScreemImp
import com.example.blogapp.Domien.Home.onListenerLong
import com.example.blogapp.R
import com.example.blogapp.data.model.Comercios
import com.example.blogapp.data.model.Producto
import com.example.blogapp.data.model.Remote.DataHomeRepo
import com.example.blogapp.data.model.SharedPreference
import com.example.blogapp.databinding.FragmentAddProductoDialogBinding
import com.example.blogapp.databinding.FragmentProductoAddBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ProductoAdd : Fragment(R.layout.fragment_producto_add) {

    private lateinit var positiveButton: Button
    private lateinit var negativeButton: Button
    private lateinit var binding: FragmentProductoAddBinding
    private val args by navArgs<ProductoAddArgs>()
    var sharePref: SharedPreference? = null
    var selectProducto = ArrayList<Comercios>()
    private var product: Producto? = null
    var json = Gson()
    private var id = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductoAddBinding.bind(view)
        binding.nameProducto.setText(args.id)
        binding.descripcion.setText(args.descripcion)
        binding.precio.setText(args.precio)
        recycler()
        initUpDate()
        /*
        * binding.actualizar.setOnClickListener {

            val inputP = Producto(
                binding.nameProducto.text.toString(),
                binding.descripcion.text.toString(),
                binding.precio.text.toString()
            )

            val datos = FirebaseFirestore.getInstance().collection("comercios").document(id)
            Log.d("TAGADDDIALOG", "recycler: $id")
            val satosinsert = datos.collection("productos").add(inputP)
                .addOnSuccessListener {

                    Toast.makeText(
                        activity,
                        "Se agrego el producto correctamente",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }.addOnFailureListener {
                    Toast.makeText(activity, "Error ${it.message}", Toast.LENGTH_SHORT)
                        .show()
                }
        }
        * */

    }


    private fun recycler() {
        sharePref = SharedPreference(requireContext())
        var doblw = 0
        if (!sharePref?.getData("id").isNullOrBlank()) {
            val type = object : TypeToken<ArrayList<Comercios>>() {}.type
            selectProducto = json.fromJson(sharePref?.getData("id"), type)
            id = selectProducto[0].email
        }
    }

    private fun initUpDate() {
        /* product = (context as? onListenerLong).onlongListener(producto!!)
        product?.let { prdocut ->
            binding?.let {
                binding.nameProducto.setText(prdocut.nombre)
                binding.descripcion.setText(prdocut.descripcion)
                binding.precio.setText(prdocut.precio)
            }
        }*/
    }
}