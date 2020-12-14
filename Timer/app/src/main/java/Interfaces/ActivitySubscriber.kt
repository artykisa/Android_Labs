package Interfaces

import database.Exercise
import database.activity

interface ActivitySubsriber {
    fun onNext(activity:activity)
    fun insertValues(list:List<activity>,exercise: Exercise)
    fun renewItems(list:MutableList<activity>)
}