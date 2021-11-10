package com.maximshuhman.LSM

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class ResDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity, R.style.Theme_Results)
        val view = layoutInflater.inflate(R.layout.results, null)
        builder.setView(view)

        val t2: TextView = view.findViewById(R.id.textView2)
        val t4: TextView = view.findViewById(R.id.textView4)
        val t6: TextView = view.findViewById(R.id.textView6)
        val t8: TextView = view.findViewById(R.id.textView8)
        val t10: TextView = view.findViewById(R.id.textView10)
        val t12: TextView = view.findViewById(R.id.textView12)
        val t14: TextView = view.findViewById(R.id.textView14)


        t2.text = requireArguments().getDouble("A").toString()
        t4.text = requireArguments().getDouble("B").toString()
        t6.text = requireArguments().getDouble("RXY").toString()
        t8.text = requireArguments().getDouble("DA").toString()
        t10.text = requireArguments().getDouble("DB").toString()
        t12.text = requireArguments().getDouble("SX2").toString()
        t14.text = requireArguments().getDouble("SY2").toString()
        return builder.create()
    }

    companion object {
        fun newInstance(
            a: Double,
            b: Double,
            rxy: Double,
            da: Double,
            db: Double,
            sx2: Double,
            sy2: Double
        ): ResDialog {
            val args = Bundle().apply {
                putDouble("A", a)
                putDouble("B", b)
                putDouble("RXY", rxy)
                putDouble("DA", da)
                putDouble("DB", db)
                putDouble("SY2", sy2)
                putDouble("SX2", sx2)
            }
            return ResDialog().apply {
                arguments = args
            }
        }
    }
}