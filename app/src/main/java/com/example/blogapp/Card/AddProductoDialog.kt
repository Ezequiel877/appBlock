package com.example.blogapp.Card

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.example.blogapp.Domien.Home.Update
import com.example.blogapp.Domien.Home.onListenerLong
import com.example.blogapp.IU.home.HomeD.FrgmentProduc
import com.example.blogapp.data.model.Comercios
import com.example.blogapp.data.model.Producto
import com.example.blogapp.data.model.SharedPreference
import com.example.blogapp.databinding.FragmentAddProductoDialogBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class AddProductoDialog : DialogFragment(), DialogInterface.OnShowListener {

    private lateinit var positiveButton: Button
    private lateinit var negativeButton: Button
    private lateinit var binding: FragmentAddProductoDialogBinding
    private val args by navArgs<AddProductoDialogArgs>()
    private var id: String = ""
    var sharePref: SharedPreference? = null
    var selectProducto = ArrayList<Comercios>()
    private var productoSelected:Producto?=null

    var json = Gson()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        activity?.let { activity ->
            binding = FragmentAddProductoDialogBinding.inflate(LayoutInflater.from(context))


            binding.let {
                val builder = AlertDialog.Builder(activity)
                    .setTitle("Agregar Productos")
                    .setPositiveButton("Agregar", null)
                    .setNegativeButton("Cancelar", null)
                    .setView(it.root)
                val dialog = builder.create()
                dialog.setOnShowListener(this)

                return dialog
            }
        }
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onShow(dialogInterface: DialogInterface?) {
        initUpDate()
        val dialogAdd = dialog as? AlertDialog
        dialogAdd?.let { it ->
            positiveButton = it.getButton(Dialog.BUTTON_POSITIVE)
            negativeButton = it.getButton(Dialog.BUTTON_NEGATIVE)
            positiveButton.setOnClickListener {
                binding.let { buttons ->
                    if (productoSelected == null) {
                        val inputP = Producto(
                            buttons.nameProducto.text.toString(),
                            buttons.descripcion.text.toString(),
                            buttons.precio.text.toString(),
                        )
                        val datos =
                            FirebaseFirestore.getInstance().collection("comercios").document(id)
                        Log.d("TAGADDDIALOG", "recycler: $id")
                        val satosinsert = datos.collection("productos").add(inputP)
                            .addOnSuccessListener {

                                Toast.makeText(
                                    activity,
                                    "Se agrego el producto correctamente",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }.addOnFailureListener {
                                Toast.makeText(activity, "Error ${it.message}", Toast.LENGTH_SHORT)
                                    .show()
                            }
                    }else{
                        productoSelected?.apply {
                            nombre=buttons.nameProducto.text.toString().trim()
                            descripcion=buttons.descripcion.text.toString().trim()
                        }
                    }
                }
            }

            negativeButton.setOnClickListener {
                dismiss()
            }
        }

        recycler()
        sharePref = SharedPreference(requireContext())
    }
    private fun initUpDate() {
        productoSelected = (context as? Update)?.UpdateProdcuto()
        Log.d("TAGDIALOG", "initUpDate: ${productoSelected}")
        productoSelected?.let { prdocut ->
            binding?.let {
                binding.nameProducto.setText(prdocut.nombre)
                binding.descripcion.setText(prdocut.descripcion)
                binding.precio.setText(prdocut.precio)
            }
        }
    }

    private fun recycler() {
        sharePref = SharedPreference(requireContext())
        var doblw = 0
        if (!sharePref?.getData("id").isNullOrBlank()) {
            val type = object : TypeToken<ArrayList<Comercios>>() {}.type
            selectProducto = json.fromJson(sharePref?.getData("id"), type)
            id = selectProducto[0].email
        }
    }


}