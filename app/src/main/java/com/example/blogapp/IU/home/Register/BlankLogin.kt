package com.example.blogapp.IU.home.Register

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
import com.example.blogapp.BlogApp.presentation.presentacion.factory
import com.example.blogapp.BlogApp.presentation.presentacion.viewModel2
import com.example.blogapp.Domien.Login.homeScremLogin
import com.example.blogapp.IU.home.home.adapter.Result
import com.example.blogapp.R
import com.example.blogapp.data.model.Comercios
import com.example.blogapp.data.model.Remote.repoLogin
import com.example.blogapp.data.model.SharedPreference
import com.example.blogapp.data.model.constantes
import com.example.blogapp.databinding.FragmentBlankLoginBinding
import com.google.firebase.firestore.FirebaseFirestore

class BlankLogin : Fragment(R.layout.fragment_blank_login) {


    var sharePref: SharedPreference? = null
    var selectProducto = ArrayList<Comercios>()
    var product: Comercios? = null
    private lateinit var binding: FragmentBlankLoginBinding
    private val modelView by viewModels<viewModel2> { factory(homeScremLogin(repoLogin())) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBlankLoginBinding.bind(view)
        getDatosIn()
        sharePref= SharedPreference(requireContext())
        var daots=8/2
        println(daots)

    }

    private fun getDatosIn() {
        binding.buttonUp.setOnClickListener {
            val user = binding.InPutusername.text.toString().trim()
            val email = binding.InPutText2.text.toString().trim()
            val password = binding.InPutTextAdress.text.toString().trim()
            val confirm = binding.InPutTexconfirm.text.toString().trim()
            //val horarios = binding.InPutHorarios.text.toString().trim()
            //val direccion = binding.InPutDireccion.text.toString().trim()
            val comercio=Comercios(user, email, password, confirm)
            if (valided(password, confirm, user, email)) return@setOnClickListener
            val preference =
                PreferenceManager.getDefaultSharedPreferences(this.context)
            val token = preference.getString(constantes.TOKEN_ID, null)
            token?.let {
                val firebase = FirebaseFirestore.getInstance()
                val tokenmap = hashMapOf(Pair(constantes.TOKEN_ID, token))
                val db = firebase.collection("comercios").document(user)
                db.collection("token").add(tokenmap).addOnSuccessListener {
                    Log.d("TAGTOKENSEGUARDO", "createToken: $token")
                    preference.edit {
                        putString(constantes.TOKEN_ID, null)
                            .apply()
                    }
                }.addOnFailureListener {
                    Log.d("TAGTOKENSEGUARDO", "createToken: $it")
                }}
            createUser(email, password, user)
            datosModelShared()
        }

    }
    private fun datosModelShared(){
        product= Comercios(binding.InPutusername.text.toString())
        selectProducto.add(product!!)
        sharePref?.save("id", selectProducto)
    }


    private fun createUser(nombre:String,email: String,pasword:String) {
        modelView.singUn(nombre, email, pasword).observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Loading -> {
                    binding.progresRigt.visibility = View.VISIBLE
                    binding.buttonUp.isEnabled = false
                }
                is Result.Succes -> {
                    binding.progresRigt.visibility = View.GONE
                    val navegacion=BlankLoginDirections.actionBlankLoginToFrgmentProduc(binding.InPutusername.text.toString())
                    findNavController().navigate(navegacion)
                }
                is Result.Failure -> {
                    binding.progresRigt.visibility = View.GONE
                    binding.buttonUp.isEnabled = true
                    Log.d("LoginResgitred", "createUser: ${it.exception}")
                    Toast.makeText(requireContext(), "error:${it.exception}", Toast.LENGTH_SHORT)
                        .show()
                }

            }
        })
    }

    private fun valided(
        password: String,
        confirm: String,
        user: String,
        email: String
    ): Boolean {
        if (password != confirm) {
            binding.InPutTextAdress.error = "no hay coincidencia"
            binding.InPutTexconfirm.error = "no hay coincidencia"
            return true

        }
        if (user.isEmpty()) {
            binding.InPutusername.error = "error:vacio"
            return true
        }
        if (email.isEmpty()) {
            binding.InPutText2.error = "error:vacio"
            return true

        }
        if (password.isEmpty()) {
            binding.InPutTextAdress.error = "error:vacio"
            return true

        }
        if (confirm.isEmpty()) {
            binding.InPutTexconfirm.error = "error:vacio"
            return true

        }
        return false
    }

}
