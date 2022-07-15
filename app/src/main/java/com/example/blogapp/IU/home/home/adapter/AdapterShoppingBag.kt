package com.example.blogapp.IU.home.home.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blogapp.Domien.Home.OnCartListener
import com.example.blogapp.R
import com.example.blogapp.data.model.Orden
import com.example.blogapp.data.model.SharedPreference
import com.example.blogapp.databinding.DetalleshppingbagBinding

class AdapterShoppingBag(private var context: Context,
                         private val producList: ArrayList<Orden>, private val listener: OnCartListener
) : RecyclerView.Adapter<AdapterShoppingBag.ViewHolder>() {

    val share = SharedPreference(context)

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = DetalleshppingbagBinding.bind(view)
        fun setListener(product: Orden) {
            binding.bntMas.setOnClickListener {
            }
            binding.bntMENOS.setOnClickListener {
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view =
            LayoutInflater.from(context).inflate(R.layout.detalleshppingbag, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = producList[position]
        holder.setListener(product)

        holder.binding.texName.text = product.name
        holder.binding.texviewPrecioFinal.text = product.totalPrice.toString()

        holder.binding.bntMas.setOnClickListener {

        }
        holder.binding.bntMENOS.setOnClickListener {

        }

    }
    fun addCarritoList():List<Orden> = producList

    override fun getItemCount(): Int = producList.size

    private fun getIndexOf(idProducto: String): Int {
        var index = 0
        for (p in producList) {
            if (p.name == idProducto) {
                Log.d("INDEXSEELOGD", "getIndexOf:$p")

                return index
                break
            }
            index++
        }
        return -1
    }
    fun getTotal(): Int {
        var total = 0
        for (s in producList) {
            total += (s.totalPrice.toInt() * s.status)
        }
        return total
    }


}
