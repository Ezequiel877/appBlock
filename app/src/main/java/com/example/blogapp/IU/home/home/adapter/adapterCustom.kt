package com.example.blogapp.IU.home.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.blogapp.databinding.AllClientesBinding
import com.example.blogapp.data.model.dataIn

class adapterCustom(private val dataIn: List<dataIn>) : RecyclerView.Adapter<BaseViewHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val viewBinding =
            AllClientesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HolderView(viewBinding, parent.context)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
when(holder){
    is HolderView ->holder.bind(dataIn[position])
}    }

    override fun getItemCount(): Int = dataIn.size
    inner class HolderView(val bindin: AllClientesBinding, val context: Context) :
        BaseViewHolder<dataIn>(bindin.root) {
        override fun bind(item: dataIn) {
            Glide.with(context).load(item.post_imagen).centerCrop().into(bindin.textViewimage)
            Glide.with(context).load(item.postTime).centerCrop().into(bindin.imagen)
            bindin.texName.text=item.id
            bindin.tex2.text=item.email


        }

    }
}
