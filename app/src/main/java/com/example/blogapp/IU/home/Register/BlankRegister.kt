package com.example.blogapp.IU.home.Register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.blogapp.BlogApp.presentation.presentacion.factory
import com.example.blogapp.BlogApp.presentation.presentacion.viewModel2
import com.example.blogapp.Domien.Login.homeScremLogin
import com.example.blogapp.IU.home.home.adapter.Result
import com.example.blogapp.R
import com.example.blogapp.data.model.Remote.repoLogin
import com.example.blogapp.databinding.FragmentBlankRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class BlankRegister : Fragment(R.layout.fragment_blank_register) {
    private lateinit var binding:FragmentBlankRegisterBinding
    private val firabaseAuth by lazy { FirebaseAuth.getInstance() }
  private val modelView by viewModels<viewModel2> { factory(homeScremLogin(repoLogin())) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentBlankRegisterBinding.bind(view)
        isUserLogin()
        doLogin()
        gotoDatos()

    }
    private fun isUserLogin(){
        firabaseAuth.currentUser?.let{
             findNavController().navigate(R.id.action_blankRegister_to_blankHome)
        }
    }
    private fun doLogin(){
        binding.buttonIn.setOnClickListener {
            val email=binding.InPutText2.text.toString().trim()
            val passwork=binding.InPutTextAdress.text.toString().trim()
            valided(email, passwork)
            singIn(email, passwork)


        }

    }
    private fun gotoDatos(){
        binding.singUp.setOnClickListener {
            findNavController().navigate(R.id.action_blankRegister_to_blankLogin)

        }

    }
    private fun valided(email:String, passwork:String){
        if (email.isEmpty()){
            binding.InPutText2.error="error, el texto es vacio"
            return
        }
        if (passwork.isEmpty()){
            binding.InPutTextAdress.error="error, el texto es vacio"
            return
        }
    }
    private fun singIn(email:String, passwork:String){
        modelView.singIn(email, passwork).observe(viewLifecycleOwner, Observer {
            when(it) {
                is Result.Loading->{
                    binding.progresRigt.visibility=View.GONE
                    binding.buttonIn.isEnabled=false

            }
                is Result.Succes ->{
                    binding.progresRigt.visibility=View.GONE
                        findNavController().navigate(R.id.action_blankRegister_to_blankHome)
                }
                is Result.Failure ->{
                    binding.progresRigt.visibility=View.GONE
                    binding.buttonIn.isEnabled=true

                    Toast.makeText(requireContext(), "error:${it.exception}", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }
}
