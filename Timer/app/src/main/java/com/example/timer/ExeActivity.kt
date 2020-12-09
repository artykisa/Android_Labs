package com.example.timer

import adapters.ExerciseAdapter
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ExeActivity : AppCompatActivity(), ExerciseAdapter.IExercixeAdapterListner {
    lateinit var recycleadapter:ExerciseAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exersice)
        recycleadapter= ExerciseAdapter(application,this,resources.getStringArray(R.array.activity_type).toList())
        val exeList=findViewById<RecyclerView>(R.id.exe_list)
        with(exeList){
            layoutManager= LinearLayoutManager(context)
            adapter=recycleadapter
        }
    }

    override fun onExerciseChoose(exeId: Int) {
        val intent= Intent()
        intent.putExtra("exeId",exeId)
        setResult(0,intent)
        finish()
    }
}