package com.example.timer

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.timer.NewExerciseViewModel

class NewExerciseViewModelFactory(val application:Application) :ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewExerciseViewModel(application) as T
    }
}
