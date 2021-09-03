package com.example.blogapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.blogapp.BlogApp.presentation.presentacion.hide
import com.example.blogapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val nasHostFragment=supportFragmentManager.findFragmentById(R.id.fragment_Container) as NavHostFragment
        val navControler=nasHostFragment.navController

binding.bottomNavigationView.setupWithNavController(navControler)
        navControler.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id){
                R.id.blankRegister->{
                binding.bottomNavigationView.hide()
                }
                R.id.blankLogin->{
                    binding.bottomNavigationView.hide()


                } R.id.blanksetupPROFILE->{
                binding.bottomNavigationView.hide()


            }else ->{
                binding.bottomNavigationView.visibility=View.VISIBLE

                }
            }
        }
    }
}