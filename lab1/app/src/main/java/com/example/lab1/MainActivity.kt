package com.example.lab1


import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_view.*



class MainActivity : AppCompatActivity() {
    fun get_adapter_weight(): ArrayAdapter<String> {
        val WeightList = resources.getStringArray(R.array.Weight)
        val adapter_weight = ArrayAdapter(
            this@MainActivity,
            android.R.layout.simple_spinner_item, WeightList
        )
        return adapter_weight
    }
    fun get_adapter_currency(): ArrayAdapter<String> {
        val CurencyList = resources.getStringArray(R.array.Currency)
        val adapter_curency = ArrayAdapter(
            this@MainActivity,
            android.R.layout.simple_spinner_item, CurencyList
        )
        return adapter_curency
    }
    fun get_adapter_distance(): ArrayAdapter<String> {
        val DistanceList = resources.getStringArray(R.array.Distance)
        val adapter_distance = ArrayAdapter(
            this@MainActivity,
            android.R.layout.simple_spinner_item, DistanceList
        )
        return adapter_distance
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun set_text(x: String) {
        val sb = StringBuilder()
        val myString = fragment.result_Text.text
        if(myString.length!=12) {
            sb.append(myString).append(x)
            fragment.result_Text.text = sb
        }
        else {
            val text = "Max length!"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
        }
    }
    fun dot_press(){
        val myString = fragment.result_Text.text
        var dot_counter = 0
        if(myString.length==0){
            val text = "Dot can't be the first symbol!"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
            return
        }
        else{
            for(x in myString){
                if(x=='.')
                    dot_counter += 1
                if(dot_counter==1){
                    val text = "There can not be 2 dots"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(applicationContext, text, duration)
                    toast.show()
                    return
                }
            }
        }
        set_text(".")
    }
    fun x_press() {
        val myString = fragment.result_Text.text
        fragment.result_Text.text = myString.dropLast(1)
    }
}