package com.maximshuhman.LSM

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class AddDialog: DialogFragment() {

    private lateinit var btnOk: Button
    private lateinit var btnCancel: Button
    private lateinit var editX: EditText
    private lateinit var editY: EditText



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity, R.style.Theme_Results)
        val view = layoutInflater.inflate(R.layout.add_value, null)
        builder.setView(view)

        val head: TextView = view.findViewById(R.id.textView12)
        btnOk = view.findViewById(R.id.btn_ok)
        btnCancel = view.findViewById(R.id.btn_cancel)
        editX = view.findViewById(R.id.edit__x)
        editY = view.findViewById(R.id.edit__y)

        editX.setText(requireArguments().getDouble("VALX").toString())
        editY.setText(requireArguments().getDouble("VALY").toString())

        val type = requireArguments().getBoolean("TYPE")

        btnOk.text = if(type) "Изменить" else "Добавить"
        head.text = if(type) "Изменить точку" else "Добавить точку"

        val valnum = requireArguments().getInt("POS")

        btnOk.setOnClickListener{
            if(type)
                 ValueList.changeValue(valnum, editX.text.toString().toDouble(), editY.text.toString().toDouble())
            else
                ValueList.addValue(editX.text.toString().toDouble(), editY.text.toString().toDouble())


            dismiss()
        }

        btnCancel.setOnClickListener{
            dismiss()
        }

        return builder.create()
    }



    companion object{
        fun newInstance(
            pos: Int,
            valX: Double,
            valY: Double,
            type: Boolean
        ): AddDialog {
            val args = Bundle().apply {
                putBoolean("TYPE", type)
                putInt("POS", pos)
                putDouble("VALX", valX)
                putDouble("VALY", valY)
            }
            return AddDialog().apply {
                arguments = args
            }
        }
    }
}