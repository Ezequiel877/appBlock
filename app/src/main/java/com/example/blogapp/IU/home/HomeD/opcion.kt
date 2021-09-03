package com.example.blogapp.IU.home.HomeD

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.blogapp.R
import com.example.blogapp.databinding.FragmentOpcionBinding

class opcion : Fragment(R.layout.fragment_opcion) {
    private lateinit var binding:FragmentOpcionBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentOpcionBinding.bind(view)
    }
}
