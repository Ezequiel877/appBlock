package com.example.blogapp.IU.home.HomeD

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.blogapp.BlogApp.presentation.presentacion.HomeScreemFactotry
import com.example.blogapp.Domien.Home.HomeScreemImp
import com.example.blogapp.IU.home.home.adapter.adapterCustom
import com.example.blogapp.R
import com.example.blogapp.data.model.Remote.DataHomeRepo
import com.example.blogapp.databinding.FragmentBlankHomeBinding
import com.example.blogapp.BlogApp.presentation.presentacion.HomeViewModel
import com.example.blogapp.Card.AddProductoDialog
import com.example.blogapp.Domien.Home.onListenerLong
import com.example.blogapp.IU.home.home.adapter.AdapterHome
import com.example.blogapp.IU.home.home.adapter.Result
import com.example.blogapp.data.model.*
import com.example.blogapp.data.model.notificaciones.UsesProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BlankHome : Fragment(R.layout.fragment_blank_home), adapterCustom.OnModelClick, onListenerLong {

    var userProvider: UsesProvider? = null
    private lateinit var binding: FragmentBlankHomeBinding
    private val args by navArgs<BlankHomeArgs>()
    private var id: String = ""
    var sharePref: SharedPreference? = null
    var selectProducto = ArrayList<Comercios>()
    private var product: Producto ? = null
    var json = Gson()

    private val viewmoel by viewModels<HomeViewModel> {
        HomeScreemFactotry(
            HomeScreemImp(DataHomeRepo())
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBlankHomeBinding.bind(view)
        recycler()
        binding.flotingNextBoton.setOnClickListener {
            AddProductoDialog().show(
                requireActivity().supportFragmentManager,
                AddProductoDialog::class.java.simpleName
            )
            Toast.makeText(context, "estas navegando", Toast.LENGTH_SHORT).show()
        }

        viewmoel.getLetterPost(id).observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> {
                    binding.deltaRelative.visibility = View.VISIBLE

                }
                is Result.Succes -> {
                    binding.deltaRelative.visibility = View.GONE
                    binding.recyclerView.adapter = AdapterHome(result.data, this)
                    Log.d("HOMEDATOSDEFIREBASE", "onViewCreated:${result.data} ")

                }
                is Result.Failure -> {
                    binding.deltaRelative.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "resultado:${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d("HOMEDATOSDEFIREBASE", "onViewCreated:${result.exception} ")


                }
            }
        })
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

    private fun createToken() {
        val preference = PreferenceManager.getDefaultSharedPreferences(this.context)
        val token = preference.getString(constantes.TOKEN_ID, null)

        token?.let {
            val firebase = FirebaseFirestore.getInstance()
            val tokenmap = hashMapOf(Pair(constantes.TOKEN_ID, token))
            val user = FirebaseAuth.getInstance().uid
            val db = firebase.collection("user").document(user.toString())
            db.collection("token").add(tokenmap).addOnSuccessListener {
                Log.d("TAGTOKENSEGUARDO", "createToken: $token")
                preference.edit {
                    putString(constantes.TOKEN_ID, null)
                        .apply()
                }
            }.addOnFailureListener {
                Log.d("TAGTOKENSEGUARDO", "createToken: $it")
            }
        }
    }


    override fun onmodelClick(model: Producto) {
          /*val itempas=BlankHomeDirections.actionBlankHomeToProductoAdd2(
              model.nombre,
              model.descripcion,
              model.precio
          )
            findNavController().navigate(itempas)
*/
    }

    override fun onClickListner(producto: Producto) {

    }
    override fun onlongListener(producto: Producto) {
        TODO("Not yet implemented")
    }
}


