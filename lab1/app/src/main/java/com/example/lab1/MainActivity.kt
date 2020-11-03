package com.example.lab1


import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity


class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        refresh()
    }

    fun refresh(){
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            var fragment = supportFragmentManager.findFragmentById(R.id.fragment4) as ViewFragment
            fragment.refresh()
        } else {
            var fragment = supportFragmentManager.findFragmentById(R.id.fragment) as ViewFragment
            fragment.refresh()
        }

    }

}