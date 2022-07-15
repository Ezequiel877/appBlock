package com.example.blogapp.IU.home.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.blogapp.Domien.Home.onListenerLong
import com.example.blogapp.IU.home.HomeD.BlankHome
import com.example.blogapp.data.model.Orden
import com.example.blogapp.databinding.AllClientesBinding
import com.example.blogapp.data.model.Producto

class adapterCustom(private val dataIn: MutableList<Producto>, private val listener:onListenerLong) :
    RecyclerView.Adapter<adapterCustom.HolderViewHome>() {

    interface OnModelClick {
        fun onmodelClick(model: Producto)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):HolderViewHome {
        val viewBinding = AllClientesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val item=HolderViewHome(viewBinding, parent.context)

        return item
    }

    override fun onBindViewHolder(holder: HolderViewHome, position: Int) {

        val listHome=dataIn[position]
        holder.datosRun(listHome)
        holder.bindin.textView3.text=listHome.descripcion
        holder.bindin.texName.text=listHome.nombre
    }
    class ProductoListener(val clickListener: (producto: Producto)->Unit) {
        fun onClick(producto: Producto)= clickListener(producto)
    }
    fun add(producto: Producto){
        if (!dataIn.contains(producto)){
            dataIn.add(producto)
            notifyItemInserted(dataIn.size -1)
        }
    }

    override fun getItemCount(): Int = dataIn.size

    inner class HolderViewHome(var bindin: AllClientesBinding, val context: Context) :
        RecyclerView.ViewHolder(bindin.root) {


        fun datosRun(producto: Producto){
            bindin.root.setOnLongClickListener {
                listener.onlongListener(producto)
                true
            }
            bindin.root.setOnClickListener {
                listener.onClickListner(producto)
            }
        }

    }

}
