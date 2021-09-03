package com.example.blogapp.IU.home.HomeD

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.blogapp.BlogApp.presentation.presentacion.factory
import com.example.blogapp.BlogApp.presentation.presentacion.factoryImp
import com.example.blogapp.BlogApp.presentation.presentacion.viewModel
import com.example.blogapp.BlogApp.presentation.presentacion.viewModel2
import com.example.blogapp.Domien.Home.upPost
import com.example.blogapp.Domien.Login.homeScremLogin
import com.example.blogapp.IU.home.home.adapter.Result
import com.example.blogapp.R
import com.example.blogapp.data.model.Remote.repoLogin
import com.example.blogapp.databinding.FragmentBlankcameraBinding

class Blankcamera : Fragment(R.layout.fragment_blankcamera) {
    private lateinit var binding: FragmentBlankcameraBinding
    private val viewmodel by viewModels<viewModel> { factoryImp(upPost(repoLogin())) }

    private var imagebitmap: Bitmap? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBlankcameraBinding.bind(view)
        binding.bitmapImage.setOnClickListener {
            openSomeActivityForResult()
        }
        binding.cargarOn.setOnClickListener {
            imagebitmap?.let {
                viewmodel.getPhoto(it, binding.profileText.text.toString().trim())
                    .observe(viewLifecycleOwner, Observer {
                        when (it) {
                            is Result.Loading -> {
                                Toast.makeText(requireContext(), "loading photo", Toast.LENGTH_SHORT).show()
                            }
                            is Result.Succes -> {
                                findNavController().popBackStack()
                            }
                            is Result.Failure -> {
                                Toast.makeText(requireContext(), "error:${it.exception}", Toast.LENGTH_SHORT).show()
                            }

                        }
                    })
            }
        }

    }

    fun openSomeActivityForResult() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLauncher.launch(takePictureIntent)
    }

    private var resultLauncher =
        this.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                val imageBitmap = data?.extras?.get("data") as Bitmap
                binding.bitmapImage.setImageBitmap(imageBitmap)
                imagebitmap = imageBitmap
            }
        }
}

