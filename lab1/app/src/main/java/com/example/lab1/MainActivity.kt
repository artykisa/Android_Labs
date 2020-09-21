package com.example.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.text.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //distance, weight, currency
        val distance: List<String> = listOf("kilometer","meter","centimeter")
        val weight: List<String> = listOf("ton","kilogram","gram")
        val currency: List<String> = listOf("BYN","DOLLAR","EURO")

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
        button_result.setOnClickListener {
            var text_to_transform = result_Text.text.toString()
            var int_value = text_to_transform.toDouble()
            int_value =int_value + 4
            result_Text2.text=int_value.toString()
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

        /*spinner_value.setOnClickListener(){
            resultText.text=spinner_value[0].toString()
        }*/
    }
}