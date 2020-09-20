package com.example.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_1.setOnClickListener {
            resultText.text = "1"
        }

        button_2.setOnClickListener {
            resultText.text = "2"
        }

        button_3.setOnClickListener {
            resultText.text = "3"
        }

        button_4.setOnClickListener {
            resultText.text = "4"
        }

        button_5.setOnClickListener {
            resultText.text = "5"
        }

        button_6.setOnClickListener {
            resultText.text = "6"
        }

        button_7.setOnClickListener {
            resultText.text = "7"
        }

        button_8.setOnClickListener {
            resultText.text = "8"
        }

        button_9.setOnClickListener {
            resultText.text = "9"
        }

        button_0.setOnClickListener {
            resultText.text = "0"
        }

        button_x.setOnClickListener {
            resultText.text = ""
        }

        button_dot.setOnClickListener {
            resultText.text = "."
        }
    }
}