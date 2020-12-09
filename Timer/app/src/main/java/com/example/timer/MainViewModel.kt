package com.example.timer

import Interfaces.ActivityListener
import Interfaces.ActivitySubsriber
import Interfaces.UDActivityListener
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import database.DbRep
import database.Exercise
import database.activity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext

class MainViewModel(val application: Application): ViewModel(), ActivityListener,UDActivityListener {

    var exerciseRepository: DbRep
    lateinit var subscriber: ActivitySubsriber
    var exersice: Exercise?
    val dispatcher= newFixedThreadPoolContext(2,"Init")
    init{
        exerciseRepository = DbRep(application)
        exersice = exerciseRepository.getLastExercise()

        if (exersice == null) {
            exersice = Exercise(1, "MyFirstExerscise", "Init",id = 1)
            exerciseRepository.addExercise(exersice!!)

        }
    }

    fun initActivity()=
        viewModelScope.launch(Dispatchers.IO) {
            delay(500L)

        }
    fun getTitle()=exersice?.name


    override fun onViewClicked(activity: activity) {
        activity.exeId= exersice!!.id!!
        exerciseRepository.addActivity(activity)
        subscriber.onNext(activity)
    }
    fun renewExercise(exeId:Int){
        exersice=exerciseRepository.getExercise(exeId)
    }
    override fun onCleared() {
        super.onCleared()

    }
    fun getActivities()=exerciseRepository.getActivities()

    fun addExercise(exe:Exercise){
        exerciseRepository.addExercise(exe)
    }
    fun addActivity(activity:activity)
    {
        exerciseRepository.addActivity(activity)
    }
    fun subscibe(sub:ActivitySubsriber)
    {
        subscriber=sub
        val exercises=exerciseRepository.getRelatedActivities(exersice!!)
        subscriber.insertValues(exercises, exersice!!)
    }

    override fun onDelete(activity: activity) {
        exerciseRepository.removeActivity(activity)
    }

    override fun update(activity: activity) {
        exerciseRepository.updateActivity(activity)
    }
    fun checkactivity():Boolean{
        exerciseRepository = DbRep(application)
        if(exersice==null)
        {
            exersice = Exercise(1, "MyFirstExerscise", "Init",id = 1)
            exerciseRepository.addExercise(exersice!!)
            return false
        }
        else {
            exersice = exerciseRepository.getExercise(exersice!!.id!!)
            if (exersice == null) {
                exersice = Exercise(1, "MyFirstExerscise", "Init", id = 1)
                exerciseRepository.addExercise(exersice!!)
                return false

            }
            else
                return true
        }
    }



}