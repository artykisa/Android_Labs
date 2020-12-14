package Interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.*
import database.Exercise


@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(exe: Exercise)

    @Update
    fun update(exe: Exercise)

    @Delete
    fun delete(exe: Exercise)

    @Query("SELECT * from exercise_table ORDER BY id")
    fun getAllExercises(): LiveData<List<Exercise>>

    @Query("SELECT * from exercise_table ORDER BY id")
    fun getExercisesList():MutableList<Exercise>

    @Query("SELECT * from exercise_table ORDER BY  id DESC LIMIT 1")
    fun getLastExercise():Exercise?

    @Query("SELECT COUNT(*) FROM exercise_table")
    fun getExeCount():Int

    @Query("SELECT * FROM exercise_table WHERE id=:exeId")
    fun getExe(exeId:Int):Exercise

    @Query("DELETE  FROM exercise_table")
    fun deleteEverything()
}