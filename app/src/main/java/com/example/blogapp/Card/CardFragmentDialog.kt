package com.example.blogapp.Card

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.blogapp.Domien.Home.OnCartListener
import com.example.blogapp.data.model.Orden
import com.example.blogapp.data.model.SharedPreference
import com.example.blogapp.databinding.FragmentCarBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CardFragmentDialog : BottomSheetDialogFragment(), OnCartListener {

    private var bindinf: FragmentCarBinding? = null
    private lateinit var mBottonSheet: BottomSheetBehavior<*>
    //private lateinit var adapter: ProductCartAdapter
    private var productSelectect: Orden? = null
    var share: SharedPreference? = null
    var IDCOMMER: String=""
    var selectProducto = ArrayList<Orden>()
    var json = Gson()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        bindinf = FragmentCarBinding.inflate(LayoutInflater.from(context))
        bindinf?.let {
            val bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
            bottomSheetDialog.setContentView(it.root)
            mBottonSheet = BottomSheetBehavior.from(it.root.parent as View)
            mBottonSheet.state = BottomSheetBehavior.STATE_EXPANDED
            gotoShopping()
            setupCancelBnt()
            recycler()
            return bottomSheetDialog
        }


        return super.onCreateDialog(savedInstanceState)
    }

    private fun setupCancelBnt() {
        bindinf?.let {
            it.imagenCancel.setOnClickListener {
                mBottonSheet.state = BottomSheetBehavior.STATE_HIDDEN
            }
        }
    }

    fun setTotal(total: Int) {
        bindinf!!.textTotal.text = "${total}"
    }

    private fun gotoShopping() {
        bindinf!!.idFlag.setOnClickListener {
            //val navegacion=CardFragmentDialogDirections.actionCardFragmentDialogToFragmentDelicery3(
              //  args.idComers
            //)
           // findNavController().navigate(navegacion)
            onDestroy()
        }
    }


    private fun recycler() {
        share = SharedPreference(requireContext())
        var doblw = 0
        if (!share?.getData("orden").isNullOrBlank()) {
            val type = object : TypeToken<ArrayList<Orden>>() {}.type
            selectProducto = json.fromJson(share?.getData("orden"), type)

            /*
            * bindinf?.let {
                adapter = context?.let { it1 -> ProductCartAdapter(it1, selectProducto, this) }!!
                it.recyclerCarf.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = this@CardFragmentDialog.adapter
                }
                for (s in selectProducto) {
                    val pp=s.precio1.toString().toInt()
                    Log.d("TAGCarritoDialog", "recycler: $s")
                    doblw +=  s.precios2
                    Log.d("TAGTOTALPRICE", "recycler: $doblw")

                }
                setTotal(doblw)
            }
            * */

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        bindinf = null
    }

    override fun setQuanty(produc: Orden) {
        TODO("Not yet implemented")
    }

    override fun showTotal(total: Int) {
        setTotal(total)
    }

}
