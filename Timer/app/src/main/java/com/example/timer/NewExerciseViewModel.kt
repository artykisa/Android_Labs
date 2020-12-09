package com.example.timer

import Interfaces.ActivityListener
import Interfaces.ActivitySubsriber
import Interfaces.UDActivityListener
import android.app.Application
import android.graphics.Color
import androidx.lifecycle.ViewModel
import database.DbRep
import database.Exercise
import database.activity

class NewExerciseViewModel(application: Application):ViewModel(), ActivityListener,
    UDActivityListener {
    var currExercise: Exercise
    var activities= mutableListOf<activity>()
    val exericeRepository= DbRep(application)
    lateinit var activitySubsriber: ActivitySubsriber
    init {
        val exeId=exericeRepository.getExerciseCount()+1
        currExercise= Exercise(Color.WHITE,"","",exeId)
    }

    override fun onViewClicked(exercise: activity) {
        exercise.exeId=currExercise.id!!
        activities.add(exercise)
        activitySubsriber.onNext(exercise)
    }
    fun Save(name:String,color:Int){
        currExercise.name=name
        currExercise.color=color
        exericeRepository.addExercise(currExercise)
        exericeRepository.insertAll(activities)

    }
    fun subscribe(subsriber: ActivitySubsriber)
    {
        activitySubsriber=subsriber
    }


    override fun onDelete(activity: activity) {
        exericeRepository.removeActivity(activity)
    }

    override fun update(activity: activity) {
        exericeRepository.updateActivity(activity)
    }

}