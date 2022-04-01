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
        val t19: TextView = view.findViewById(R.id.textView19)
        val t21: TextView = view.findViewById(R.id.textView21)



        t2.text =  String.format( "%.5f", requireArguments().getDouble("A"))
        t4.text =  String.format( "%.5f", requireArguments().getDouble("B"))
        t6.text =  String.format( "%.5f", requireArguments().getDouble("RXY"))
        t8.text =  String.format( "%.5f",  requireArguments().getDouble("DA"))
        t10.text = String.format("%.5f", requireArguments().getDouble("DB"))
        t12.text = String.format("%.5f", requireArguments().getDouble("SX2"))
        t14.text = String.format("%.5f", requireArguments().getDouble("SY2"))
        t19.text = String.format("%.5f", requireArguments().getDouble("EA"))
        t21.text = String.format("%.5f", requireArguments().getDouble("EB"))

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
            sy2: Double,
            ea: Double,
            eb: Double
        ): ResDialog {
            val args = Bundle().apply {
                putDouble("A", a)
                putDouble("B", b)
                putDouble("RXY", rxy)
                putDouble("DA", da)
                putDouble("DB", db)
                putDouble("SY2", sy2)
                putDouble("SX2", sx2)
                putDouble("EA", ea)
                putDouble("EB", eb)
            }
            return ResDialog().apply {
                arguments = args
            }
        }
    }
}