package com.example.blogapp.IU.home.HomeD

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.GridLayout
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.blogapp.BlogApp.presentation.presentacion.HomeScreemFactotry
import com.example.blogapp.BlogApp.presentation.presentacion.HomeViewModel
import com.example.blogapp.Card.AddProductoDialog
import com.example.blogapp.Domien.Home.HomeScreemImp
import com.example.blogapp.Domien.Home.Update
import com.example.blogapp.Domien.Home.onListenerLong
import com.example.blogapp.IU.home.home.adapter.Result
import com.example.blogapp.IU.home.home.adapter.adapterCustom
import com.example.blogapp.R
import com.example.blogapp.data.model.Comercios
import com.example.blogapp.data.model.Producto
import com.example.blogapp.data.model.Remote.DataHomeRepo
import com.example.blogapp.data.model.SharedPreference
import com.example.blogapp.data.model.constantes
import com.example.blogapp.data.model.notificaciones.UsesProvider
import com.example.blogapp.databinding.FragmentFrgmentProducBinding
import com.example.kampasmobil2.Volley.NotificationS
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class FrgmentProduc : Fragment(R.layout.fragment_frgment_produc), onListenerLong,Update {

    var userProvider: UsesProvider? = null
    private lateinit var binding: FragmentFrgmentProducBinding
    private val args by navArgs<FrgmentProducArgs>()
    private var id: String = ""
    private var sharePref: SharedPreference? = null
    private var selectProducto = ArrayList<Comercios>()
    private var product: Comercios? = null
    private var json = Gson()
    private lateinit var adapter:adapterCustom
    private var productoSelected:Producto?=null


    private val viewmoel by viewModels<HomeViewModel> {
        HomeScreemFactotry(
            HomeScreemImp(DataHomeRepo())
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFrgmentProducBinding.bind(view)
        recycler()
        gotoToDialog()
        adapter= adapterCustom(mutableListOf(), this)
        binding.recyclerView.apply {
            layoutManager=GridLayoutManager(context, 3, GridLayoutManager.HORIZONTAL, false)
            adapter=this@FrgmentProduc.adapter
        }
        val datos = FirebaseFirestore.getInstance().collection("comercios").document(id)
        val input = datos.collection("productos").get().addOnSuccessListener {
            for (s in it){
                val productoGet=s.toObject(Producto::class.java)
                productoGet.id=s.id
                adapter.add(productoGet)
            }

        }


/*
*
            viewmoel.getLetterPost(id).observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> {

                }
                is Result.Succes -> {
                    binding.recyclerView.adapter = adapterCustom(result.data, this)
                    Log.d("HOMEDATOSDEFIREBASE", "onViewCreated:${result.data} ")

                }
                is Result.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        "resultado:${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
        })
* */
    }

    private fun gotoToDialog() {
        binding.flotingNextBoton.setOnClickListener {
            productoSelected=null
            AddProductoDialog().show(
                parentFragmentManager,
                AddProductoDialog::class.java.simpleName
            )
            Toast.makeText(context, "estas navegando", Toast.LENGTH_SHORT).show()
        }
    }

    /*
    * */


    private fun recycler() {
        sharePref = SharedPreference(requireContext())
        var doblw = 0
        if (!sharePref?.getData("id").isNullOrBlank()) {
            val type = object : TypeToken<ArrayList<Comercios>>() {}.type
            selectProducto = json.fromJson(sharePref?.getData("id"), type)
            id = selectProducto[0].email
            Log.d("TAGDELIDPARAGET", "recycler:$id ")

        }
    }

    override fun onClickListner(producto: Producto) {
       val navegacvion=FrgmentProducDirections.actionFrgmentProducToProductoAdd2(producto.nombre, producto.descripcion, producto.precio)
        findNavController().navigate(navegacvion)
    }
    override fun UpdateProdcuto(): Producto?=productoSelected

    override fun onlongListener(producto: Producto) {
        val datos = FirebaseFirestore.getInstance().collection("comercios").document(id)
        val input = datos.collection("productos")
        producto.id.let { idPorcut ->
            input.document(idPorcut).delete()
        }
    }

}




/*/input.document(idPorcut).delete().addOnFailureListener {
           private fun createToken() {
        val preference = PreferenceManager.getDefaultSharedPreferences(this.context)
        val token = preference.getString(constantes.TOKEN_ID, null)

        token?.let {
            val firebase = FirebaseFirestore.getInstance()
            val tokenmap = hashMapOf(Pair(constantes.TOKEN_ID, token))
            val user = FirebaseAuth.getInstance().uid
            val db = firebase.collection("user").document(id)
            db.collection("token").add(tokenmap).addOnSuccessListener {
                Log.d("TAGTOKENSEGUARDO", "createToken: $token")
                val notifi = NotificationS()
                notifi.sendNotication(",", user.toString(), "")
                preference.edit {
                    putString(constantes.TOKEN_ID, null)
                        .apply()
                }
            }.addOnFailureListener {
                Log.d("TAGTOKENSEGUARDO", "createToken: $it")
            }
        }
    }      Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
           * */

