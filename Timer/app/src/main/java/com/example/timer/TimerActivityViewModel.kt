package com.example.timer

import adapters.RecyclerAdapter
import android.app.Application
import androidx.lifecycle.ViewModel
import database.DbRep
import database.Exercise
import database.activity

class TimerActivityViewModel(val application: Application, exeId:Int): ViewModel(), RecyclerAdapter.RecyclerViewSupplier {
    var currExercise: Exercise
    var exeActivities: List<activity>
    val exericeRepository= DbRep(application)
    var currnetActivity=0
    init {
        currExercise=exericeRepository.getExercise(exeId)
        exeActivities=exericeRepository.getRelatedActivities(currExercise)
    }
    fun onNext()=currnetActivity++
    override fun getActivities(): List<activity> {
        return exeActivities
    }

    override fun getExersice(): Exercise {
        return  currExercise
    }
    fun canNext()=currnetActivity<exeActivities.size
    fun getCurrentActivity()=exeActivities[currnetActivity]
    fun onReset(){
        exeActivities=exericeRepository.getRelatedActivities(currExercise)
        currnetActivity=0
    }
    fun onPrev(){
        currnetActivity--
    }
}