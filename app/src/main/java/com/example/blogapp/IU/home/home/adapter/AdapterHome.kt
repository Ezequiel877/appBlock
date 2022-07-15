package com.example.blogapp.IU.home.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.blogapp.Domien.Home.onListenerLong
import com.example.blogapp.IU.home.HomeD.BlankHome
import com.example.blogapp.data.model.Producto
import com.example.blogapp.databinding.AllClientesBinding

class AdapterHome(private val dataIn: List<Producto>, private val listener: onListenerLong) :
    RecyclerView.Adapter<AdapterHome.HolderViewHome>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderViewHome {
        val viewBinding = AllClientesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val item=HolderViewHome(viewBinding, parent.context)

        return item
    }

    override fun onBindViewHolder(holder: HolderViewHome, position: Int) {
        val listHome=dataIn[position]
        holder.datosRun(listHome)
    }

    override fun getItemCount(): Int = dataIn.size
    inner class HolderViewHome(val bindin: AllClientesBinding, val context: Context) :
        RecyclerView.ViewHolder(bindin.root) {




            fun datosRun(producto: Producto){
                bindin.root.setOnClickListener {
                    listener.onClickListner(producto)
                }
            }

        }

    }

