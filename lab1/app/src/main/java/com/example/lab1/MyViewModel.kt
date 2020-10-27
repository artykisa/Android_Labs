package com.example.lab1

import android.content.Context
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModel
import android.content.res.Resources
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.Toast

class MyViewModel : ViewModel() {

    public var number = "1"
    public var number_conv = "1"

    fun get_adapter_weight(context: Context): ArrayAdapter<String> {
        val WeightList = context.getResources().getStringArray(R.array.Weight)
        val adapter_weight = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_item,
            WeightList
        )
        return adapter_weight
    }

    fun get_adapter_currency(context: Context): ArrayAdapter<String> {
        val CurencyList = context.getResources().getStringArray(R.array.Currency)
        val adapter_curency = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_item,
            CurencyList
        )
        return adapter_curency
    }

    fun get_adapter_distance(context: Context): ArrayAdapter<String> {
        val DistanceList = context.getResources().getStringArray(R.array.Distance)
        val adapter_distance = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_item,
            DistanceList
        )
        return adapter_distance
    }

    fun set_text(x: String,context: Context) {
        val sb = StringBuilder()
        if(number.length!=12) {
            sb.append(number).append(x)
        }
        else {
            val text = "Max length!"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()
        }
    }
    fun dot_press(context: Context){
        var dot_counter = 0
        if(number.length==0){
            val text = "Dot can't be the first symbol!"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()
            return
        }
        else{
            for(x in number){
                if(x=='.')
                    dot_counter += 1
                if(dot_counter==1){
                    val text = "There can not be 2 dots"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(context, text, duration)
                    toast.show()
                    return
                }
            }
        }
        set_text(".", context)
    }

    fun x_press() {
        number = number.dropLast(1)
    }

    fun change(){
        var temp = number
        number = number_conv
        number_conv = temp
    }
}