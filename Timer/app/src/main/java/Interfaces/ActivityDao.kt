package Interfaces
import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.room.*
import database.Exercise
import database.activity

@Dao
interface ActivityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(activity:activity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(activities:List<activity>)

    @Update
    fun update(activity:activity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateAll(activities:MutableList<activity>)

    @Delete
    fun delete(activity:activity)

    @Query("delete from activities_table")
    fun deleteAllExe()

    @Query("SELECT * from activities_table ORDER BY number")
    fun getExercises(): LiveData<List<activity>>

    @Query("SELECT * from activities_table WHERE exeId=:exeId")
    fun getRelatedActivities(exeId:Int):List<activity>

    @Query("SELECT * from activities_table WHERE exeId=:exeId LIMIT :N")
    fun getRelatedActivitiesN(exeId:Int,N:Int):List<activity>

}