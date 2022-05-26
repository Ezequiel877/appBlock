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
import com.example.blogapp.data.model.Producto
import com.example.blogapp.data.model.SharedPreference
import com.example.blogapp.data.model.user
import com.example.blogapp.databinding.FragmentAddProductoDialogBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class AddProductoDialog : DialogFragment(), DialogInterface.OnShowListener {

    private lateinit var positiveButton: Button
    private lateinit var negativeButton: Button
    private lateinit var binding: FragmentAddProductoDialogBinding
    private var id: String = ""
    var sharePref: SharedPreference? = null
    var selectProducto = ArrayList<user>()
    var product: Producto? = null
    var json = Gson()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        activity?.let {activity->
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
        val dialogAdd = dialog as? AlertDialog
        dialogAdd?.let { it ->
            positiveButton = it.getButton(Dialog.BUTTON_POSITIVE)
            negativeButton = it.getButton(Dialog.BUTTON_NEGATIVE)
            positiveButton.setOnClickListener {
                binding.let { buttons ->
                    val inputP= Producto(
                        buttons.nameProducto.text.toString(),
                        buttons.descripcion.text.toString(),
                        buttons.precio.text.toString().toInt(),
                        buttons.cantidad.text.toString().toInt()
                    )
                    val datos = FirebaseFirestore.getInstance().collection("comercios").document(id)
                    Log.d("TAGADDDIALOG", "recycler: $id")
                    val satosinsert =datos.collection("productos").add(inputP)
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
                }
            }

            negativeButton.setOnClickListener {
                dismiss()
            }
        }
        recycler()
        sharePref = SharedPreference(requireContext())
    }

    private fun recycler() {
        sharePref = SharedPreference(requireContext())
        var doblw = 0
        if (!sharePref?.getData("id").isNullOrBlank()) {
            val type = object : TypeToken<ArrayList<user>>() {}.type
            selectProducto = json.fromJson(sharePref?.getData("id"), type)
            id = selectProducto[0].email
        }
    }
}