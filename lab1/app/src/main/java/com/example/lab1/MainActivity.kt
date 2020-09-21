package com.example.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

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

        button_dot.setOnClickListener {
            set_text(".")
        }
    }
}