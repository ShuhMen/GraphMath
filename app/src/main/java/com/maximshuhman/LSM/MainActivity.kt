package com.maximshuhman.LSM

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    lateinit var ValuesRecyclerView: RecyclerView
    lateinit var CalcButton: Button
    lateinit var AddValue: Button
    lateinit var DelButton: Button
    lateinit var EmptyText: TextView
    lateinit var toolBar: Toolbar

    var mode: Boolean = false

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
            updateUI(ValueList.data)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        Utils.onActivityCreateSetTheme(this);

        ValuesRecyclerView = findViewById(R.id.val_recycler_view)
        CalcButton = findViewById(R.id.calc_button)
        AddValue = findViewById(R.id.add_value)
        DelButton = findViewById(R.id.del_button)

        ValuesRecyclerView.layoutManager = LinearLayoutManager(this)

        CalcButton.setOnClickListener{

            if(ValueList.data.size != 0) {
                var sx = 0.0
                var sx2 = 0.0

                var sy = 0.0
                var sy2 = 0.0

                var spr = 0.0


                for (i in 0 until ValueList.data.size) {
                    sx += ValueList.data[i].first
                    sy += ValueList.data[i].second

                    sx2 += ValueList.data[i].first * ValueList.data[i].first
                    sy2 += ValueList.data[i].second * ValueList.data[i].second

                    spr += ValueList.data[i].first * ValueList.data[i].second
                }
                val dx = sx / ValueList.data.size
                val dy = sy / ValueList.data.size

                val Sx2 =
                    sx2 / ValueList.data.size - (sx / ValueList.data.size) * (sx / ValueList.data.size)
                val Sy2 =
                    sy2 / ValueList.data.size - (sy / ValueList.data.size) * (sy / ValueList.data.size)
                val Rxy = spr / ValueList.data.size - dx * dy

                val a = Rxy / Sx2
                val b = (sy - a * sx) / ValueList.data.size

                val da = 2 * sqrt((1 / (1.0 * (ValueList.data.size - 2))) * ((Sy2 / Sx2) - a * a))
                val db = da * sqrt(sx2 / (1.0 * ValueList.data.size))

                val dialog = ResDialog.newInstance(
                    if (!a.isNaN()) a else 0.0,
                    if (!b.isNaN()) b else 0.0,
                    if (!Rxy.isNaN()) Rxy else 0.0,
                    if (!da.isNaN()) da else 0.0,
                    if (!db.isNaN()) db else 0.0,
                    if (!Sx2.isNaN()) Sx2 else 0.0,
                    if (!Sy2.isNaN()) Sy2 else 0.0
                )

                dialog.show(this.supportFragmentManager, "ADD_OG")
            }else
            {
                val toast = Toast(applicationContext)
                toast.setText( "Введите данные")
                toast.setGravity(Gravity.TOP, 0, 0)
                toast.show()
            }
        }

        AddValue.setOnClickListener {

            val dialog = AddDialog.newInstance(ValueList.data.size, 0.0, 0.0, false)

            dialog.show(this.supportFragmentManager, "ADD_DIALOG")
        }

        DelButton.setOnClickListener{

            if(!mode){

                val toast = Toast(applicationContext)
                DelButton.backgroundTintList = ColorStateList.valueOf(getColor(R.color.red_error))
                toast.setText( "Режим удаления точек включен")
                toast.setGravity(Gravity.TOP, 0, 0)
                toast.show()
            }else{
                val toast = Toast(applicationContext)
                toast.setText( "Режим удаления точек выключен")
                toast.setGravity(Gravity.TOP, 0, 0)
                toast.show()
                DelButton.backgroundTintList = ColorStateList.valueOf(getColor(R.color.red_error))
                DelButton.backgroundTintList = ColorStateList.valueOf(getColor(R.color.blue))
            }
                mode = !mode

        }

        updateUI(ValueList.data)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings-> {


                setTheme(R.style.Pink);
                val intent = Intent(this, SettingsActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    fun updateUI(data:ArrayList<Pair<Double, Double>>){
        ValuesRecyclerView.adapter = ValuesAdapter(data)
        EmptyText = findViewById(R.id.empty_text)
        if(ValueList.data.size != 0) {
            EmptyText.visibility = View.GONE
        }
        else{
            EmptyText.visibility = View.VISIBLE
        }
    }


    inner class ValuesAdapter(val dataSet: java.util.ArrayList<Pair<Double, Double>>) :
        RecyclerView.Adapter<ValuesAdapter.ValuesHolder>() {


        inner class ValuesHolder(view: View) : RecyclerView.ViewHolder(view), View.OnLongClickListener, View.OnClickListener {

            private  var ValNumText: TextView = view.findViewById(R.id.val_number_text)
            private var ValXText: TextView = view.findViewById(R.id.val_x_text)
            private var ValYText: TextView = view.findViewById(R.id.val_y_text)

            init {
                itemView.setOnLongClickListener(this)
                itemView.setOnClickListener(this)
            }

            fun bind(posi: Int) {
                ValNumText.text = posi.toString()
                ValXText.text = dataSet[posi].first.toString()
                ValYText.text = dataSet[posi].second.toString()

            }

            override fun onLongClick(v: View?): Boolean {
                val dialog = AddDialog.newInstance(ValNumText.text.toString().toInt(), ValXText.text.toString().toDouble(), ValYText.text.toString().toDouble(), true)

                dialog.show(supportFragmentManager, "ADD_DIALOG")
                return false
            }

            override fun onClick(v: View?) {
                if(mode){
                   ValueList.data.removeAt(ValNumText.text.toString().toInt())
                    updateUI(ValueList.data)
                }
            }

        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValuesHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_of_list, parent, false)

            return ValuesHolder(view)
        }

        override fun onBindViewHolder(holder: ValuesHolder, position: Int) {
            holder.bind(position)
        }

        override fun getItemCount(): Int = dataSet.size


    }
}