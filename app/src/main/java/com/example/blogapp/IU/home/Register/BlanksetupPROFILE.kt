package com.example.blogapp.IU.home.Register

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.blogapp.BlogApp.presentation.presentacion.factory
import com.example.blogapp.BlogApp.presentation.presentacion.viewModel2
import com.example.blogapp.Domien.Login.homeScremLogin
import com.example.blogapp.IU.home.home.adapter.Result
import com.example.blogapp.R
import com.example.blogapp.data.model.Remote.repoLogin
import com.example.blogapp.databinding.FragmentBlanksetupPROFILEBinding

class BlanksetupPROFILE : Fragment(R.layout.fragment_blanksetup_p_r_o_f_i_l_e) {
    private lateinit var binding: FragmentBlanksetupPROFILEBinding
    private val viewmodel by viewModels<viewModel2> { factory(homeScremLogin(repoLogin())) }

    private var imagenbitmap: Bitmap? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBlanksetupPROFILEBinding.bind(view)
        binding.profileImage.setOnClickListener {
            openSomeActivityForResult()
        }

        binding.cargar.setOnClickListener {
            val user = binding.profile.text.toString().trim()
            val alerd = AlertDialog.Builder(requireContext()).setTitle("loading....").create()
            imagenbitmap?.let { dt ->
                if (user.isNotEmpty()) {
                    viewmodel.getPhoto(imagenBitmap = dt, name = user)
                        .observe(viewLifecycleOwner) { result ->
                            when (result) {
                                is Result.Loading -> {
                                    alerd.show()

                                }
                                is Result.Succes -> {
                                    alerd.dismiss()
                                }
                                is Result.Failure -> {
                                    alerd.dismiss()


                                }

                            }
                        }
                }
            }
        }

    }

    fun openSomeActivityForResult() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLauncher.launch(takePictureIntent)
    }


    private var resultLauncher =
       this.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode ==  Activity.RESULT_OK) {
                val data = result.data
                val imageBitmap = data?.extras?.get("data") as Bitmap
                binding.profileImage.setImageBitmap(imageBitmap)
                imagenbitmap=imageBitmap
            }
        }
}