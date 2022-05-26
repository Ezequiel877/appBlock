package com.example.blogapp.IU.home.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.blogapp.Domien.Home.onListenerLong
import com.example.blogapp.databinding.AllClientesBinding
import com.example.blogapp.data.model.DataSource
import com.example.blogapp.data.model.Producto

class adapterCustom(private val dataIn: List<Producto>, private val listener:onListenerLong) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val viewBinding =
            AllClientesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HolderView(viewBinding, parent.context)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {

        when (holder) {
            is HolderView -> holder.bind(dataIn[position])
        }
    }

    override fun getItemCount(): Int = dataIn.size
    inner class HolderView(val bindin: AllClientesBinding, val context: Context) :
        BaseViewHolder<Producto>(bindin.root) {
        override fun bind(item: Producto) {
           // Glide.with(context).load(item.cantidad).centerCrop().into(bindin.textViewimage)
            //Glide.with(context).load(item.cantidad).centerCrop().into(bindin.imagen)
            bindin.textView3.text=item.descripcion.toString()
            bindin.texName.text = item.name.toString()
            bindin.tex2.text = item.descripcion.toString()
           setListenr(item)


        }
        fun setListenr(product:Producto){
            bindin.root.setOnClickListener { listener.onClickListner(product) }
            bindin.root.setOnClickListener{ listener.onlongListener(product)
                true
            }
        }
    }
}
