package com.example.lab1


import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*distance, weight, currency
        val distance: List<String> = listOf("kilometer","meter","centimeter")
        val weight: List<String> = listOf("ton","kilogram","gram")
        val currency: List<String> = listOf("BYN","DOLLAR","EURO")*/

        fun set_text(x: String) {
            val sb = StringBuilder()
            val myString = result_Text.text
            if(myString.length!=12) {
                sb.append(myString).append(x)
                result_Text.text = sb
            }
            else {
                val text = "Max length!"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            }
        }

        button_change.setOnClickListener {
            var text_string_from = result_Text.text
            var text_string_to = result_Text2.text
            result_Text.text = text_string_to
            result_Text2.text = text_string_from
            // add for spinner
        }

        button_result.setOnClickListener {
            var text_to_transform = result_Text.text.toString()
            if(text_to_transform==""){
                val text = "No data!"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
                result_Text2.text=""
            }
            else {
                var int_value = text_to_transform.toDouble()
                if(spinner_value.selectedItem.toString()=="Weight"){
                    if(spinner_choose2.selectedItem.toString()=="Gram"){
                        if(spinner_choose.selectedItem.toString()=="Kilogram"){
                            int_value /= 1000
                        }
                        else if(spinner_choose.selectedItem.toString()=="Gram"){
                            int_value = int_value
                        }
                        else{
                            int_value /= 1000000
                        }
                    }
                    else if(spinner_choose2.selectedItem.toString()=="Kilogram"){
                        if(spinner_choose.selectedItem.toString()=="Kilogram"){
                            int_value = int_value
                        }
                        else if(spinner_choose.selectedItem.toString()=="Gram"){
                            int_value = int_value * 1000
                        }
                        else{
                            int_value /= 1000
                        }
                    }
                    else{
                        if(spinner_choose.selectedItem.toString()=="Kilogram"){
                            int_value /= 1000
                        }
                        else if(spinner_choose.selectedItem.toString()=="Gram"){
                            int_value /= 1000000
                        }
                        else{
                            int_value = int_value
                        }
                    }
                }
                else if(spinner_value.selectedItem.toString()=="Distance"){
                        if(spinner_choose2.selectedItem.toString()=="Centimeter"){
                            if(spinner_choose.selectedItem.toString()=="Meter"){
                                int_value /= 100
                            }
                            else if(spinner_choose.selectedItem.toString()=="Centimeter"){
                                int_value = int_value
                            }
                            else{
                                int_value /= 100000
                            }
                        }
                        else if(spinner_choose2.selectedItem.toString()=="Meter"){
                            if(spinner_choose.selectedItem.toString()=="Meter"){
                                int_value = int_value
                            }
                            else if(spinner_choose.selectedItem.toString()=="Centimeter"){
                                int_value = int_value * 100
                            }
                            else{
                                int_value /= 1000
                            }
                        }
                    else{
                            if(spinner_choose.selectedItem.toString()=="Meter"){
                                int_value *= 1000
                            }
                            else if(spinner_choose.selectedItem.toString()=="Centimeter"){
                                int_value *= 100000
                            }
                            else{
                                int_value = int_value
                            }
                        }
                }
                else {
                    if (spinner_choose2.selectedItem.toString() == "BYN") {
                        if (spinner_choose.selectedItem.toString() == "BYN") {
                            int_value = int_value
                        } else if (spinner_choose.selectedItem.toString() == "Dollar") {
                            int_value /= 2.6
                        } else {
                            int_value /= 3.17
                        }
                    }
                    else if (spinner_choose2.selectedItem.toString() == "Dollar") {
                        if (spinner_choose.selectedItem.toString() == "BYN") {
                            int_value *= 2.6
                        }
                        else if (spinner_choose.selectedItem.toString() == "Dollar") {
                            int_value = int_value
                        }
                        else {
                            int_value /= 1.17
                        }
                    }
                    else{
                        if (spinner_choose.selectedItem.toString() == "BYN") {
                            int_value *= 3.1
                        }
                        else if (spinner_choose.selectedItem.toString() == "Dollar") {
                            int_value = int_value * 1.17
                        }
                        else {
                            int_value = int_value
                        }
                    }
                }

                result_Text2.text = int_value.toString()
            }
        }

        button_1.setOnClickListener {
            set_text("1")
        }

        button_2.setOnClickListener {
            set_text("2")
        }

        button_3.setOnClickListener {
            set_text("3")
        }

        button_4.setOnClickListener {
            set_text("4")
        }

        button_5.setOnClickListener {
            set_text("5")
        }

        button_6.setOnClickListener {
            set_text("6")
        }

        button_7.setOnClickListener {
            set_text("7")
        }

        button_8.setOnClickListener {
            set_text("8")
        }

        button_9.setOnClickListener {
            set_text("9")
        }

        button_0.setOnClickListener {
            set_text("0")
        }

        button_x.setOnClickListener {
            val myString = result_Text.text
            result_Text.text = myString.dropLast(1)
        }

        fun dot_press(){
            val myString = result_Text.text
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

        button_dot.setOnClickListener {
            dot_press()
        }

        spinner_value.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                if(spinner_value.selectedItem.toString()=="Weight"){
                    val WeightList = resources.getStringArray(R.array.Weight)
                    val adapter_weight = ArrayAdapter(
                        this@MainActivity,
                        android.R.layout.simple_spinner_item, WeightList
                    )
                    spinner_choose.adapter=adapter_weight
                    spinner_choose2.adapter=adapter_weight
                }
                else if(spinner_value.selectedItem.toString()=="Distance"){
                    val DistanceList = resources.getStringArray(R.array.Distance)
                    val adapter_distance = ArrayAdapter(
                        this@MainActivity,
                        android.R.layout.simple_spinner_item, DistanceList
                    )
                    spinner_choose.adapter=adapter_distance
                    spinner_choose2.adapter=adapter_distance
                }
                else{
                    val CurencyList = resources.getStringArray(R.array.Currency)
                    val adapter_curency = ArrayAdapter(
                        this@MainActivity,
                        android.R.layout.simple_spinner_item, CurencyList
                    )
                    spinner_choose.adapter=adapter_curency
                    spinner_choose2.adapter=adapter_curency
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                result_Text2.text="JOPA"
            }
        }

        /*spinner_value.setOnClickListener(){
            resultText.text=spinner_value[0].toString()
        }*/
    }
}