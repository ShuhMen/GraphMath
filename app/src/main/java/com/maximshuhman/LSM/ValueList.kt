package com.maximshuhman.LSM

import androidx.lifecycle.ViewModel

object ValueList : ViewModel() {

    var data = ArrayList<Pair<Double, Double>>()

    public fun addValue(x: Double, y: Double){

        data.add(Pair(x, y))

    }

    fun deleteValue(pos: Int){
        data.removeAt(pos)
    }
    fun changeValue(pos: Int,x: Double, y: Double){
        data[pos] = Pair(x, y)

    }
}