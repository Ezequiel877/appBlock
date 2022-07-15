package com.example.blogapp.IU.home.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.blogapp.Domien.Home.OrdenInterface
import com.example.blogapp.R
import com.example.blogapp.data.model.Orden
import com.example.blogapp.databinding.IteOrdenInflateBinding

class OrdenAdapter(
    private val ordenList: MutableList<Orden>,
    private val listener: OrdenInterface
) : RecyclerView.Adapter<OrdenAdapter.ViewHolder>() {

    private lateinit var context:Context
    private val aValues:Array<String> by lazy {
        context.resources.getStringArray(R.array.status_value)
    }
    private val aStatus:Array<Int> by lazy {
        context.resources.getIntArray(R.array.status_key).toTypedArray()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
     context=parent.context
        val inflate=LayoutInflater.from(context).inflate(R.layout.ite_orden__inflate, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val orden=ordenList[position]
        holder.clicker(orden)
        holder.binding.tid.text=orden.name

        var name=""
        orden.product.forEach{
            name += "${it.value.name}"
        }

        holder.binding.nombredelosproduct.text=name
        holder.binding.totaprecio.text=orden.totalPrice.toString()
        val index=aStatus.indexOf(orden.status)
        val statusAdapter=ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, aValues)
        holder.binding.channllerStatus.setAdapter(statusAdapter)
        if (index != -1){
            holder.binding.channllerStatus.setText(aValues[index], false)
        }else{
            holder.binding.channllerStatus.setText(context.getString(R.string.orden_track_), false)
        }
    }

    override fun getItemCount(): Int =ordenList.size

    fun add(orden: Orden){
        ordenList.add(orden)
        notifyItemInserted(ordenList.size - 1)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding = IteOrdenInflateBinding.bind(view)

        fun clicker(orden: Orden) {
            binding.chip1.setOnCloseIconClickListener {
                listener.onChatOrnde(orden)
            }
            binding.btnorden.setOnClickListener {
                listener.orden(orden)
            }
        }

    }

}