package com.example.blogapp.IU.home.HomeD

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.blogapp.BlogApp.presentation.presentacion.HomeScreemFactotry
import com.example.blogapp.Domien.Home.HomeScreemImp
import com.example.blogapp.IU.home.home.adapter.adapterCustom
import com.example.blogapp.R
import com.example.blogapp.data.model.Remote.DataHomeRepo
import com.example.blogapp.databinding.FragmentBlankHomeBinding
import com.example.blogapp.BlogApp.presentation.presentacion.HomeViewModel
import com.example.blogapp.IU.home.home.adapter.Result

class BlankHome : Fragment(R.layout.fragment_blank_home) {
    private lateinit var binding: FragmentBlankHomeBinding
    private val viewmoel by viewModels<HomeViewModel> {
 HomeScreemFactotry(
            HomeScreemImp(DataHomeRepo())
        )
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentBlankHomeBinding.bind(view)
        viewmoel.getLetterPost().observe(viewLifecycleOwner, Observer {result->
            when(result){
                is Result.Loading->{
                    binding.deltaRelative.visibility=View.VISIBLE

                }
                is Result.Succes->{
                    binding.deltaRelative.visibility=View.GONE
                    binding.recyclerView.adapter=adapterCustom(result.data)


                }
                is Result.Failure->{
                    Toast.makeText(requireContext(), "resultado:${result.exception}", Toast.LENGTH_SHORT).show()

                }
            }
        })
    }
}


