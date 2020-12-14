package Interfaces

import database.Exercise
import database.activity

interface UDActivityListener {
    fun onDelete(activity: activity)
    fun update(activity: activity)
}