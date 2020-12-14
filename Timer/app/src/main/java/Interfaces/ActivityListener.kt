package Interfaces

import database.activity

interface ActivityListener {
    fun onViewClicked(exercise: activity)
}