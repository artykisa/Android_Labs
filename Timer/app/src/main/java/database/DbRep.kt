package database

import Interfaces.ActivityDao
import Interfaces.ExerciseDao
import android.app.Application
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class DbRep(application: Application): CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var exerciseDao: ExerciseDao
    private var activityDao: ActivityDao

    init{
        val db= MyDatabase.getInstance(application)
        exerciseDao=db.exerciseDao()
        activityDao=db.activityDao()


    }
    fun getExercises()=launch{exerciseDao.getAllExercises()}
    fun getLastExercise()=
        runBlocking {
            return@runBlocking getLastBG()
        }
    fun removeExercise(exe:Exercise) {
        launch { deleteExercise(exe) }
    }
    private suspend fun deleteExercise(exe:Exercise){
        withContext(Dispatchers.IO) { exerciseDao.delete(exe) }
    }

    fun addExercise(exe:Exercise)
    {
        launch {
            addExerciseBG(exe)
        }
    }
    private suspend fun getLastBG(): Exercise? {
        var exe:Exercise?=null
        withContext(Dispatchers.IO){
            exe=exerciseDao.getLastExercise()
        }
        return exe
    }
    private suspend fun addExerciseBG(exe:Exercise)
    {
        withContext(Dispatchers.IO){
            exerciseDao.insert(exe)
        }

    }

    fun getActivities()= runBlocking {
        return@runBlocking withContext(Dispatchers.IO)
        {
            return@withContext activityDao.getExercises()
        }
    }

    fun addActivity(activity:activity)
    {
        launch {
            addActivityBG(activity)
        }
    }


    private suspend fun addActivityBG(activity:activity){
        withContext(Dispatchers.IO){
            activityDao.insert(activity)
        }
    }


    fun getRelatedActivities(exe:Exercise)= runBlocking {
        return@runBlocking withContext(Dispatchers.IO){
            return@withContext activityDao.getRelatedActivities(exe.id!!)
        }
    }

    fun insertAll(activities:List<activity>){
        launch {
            insertAllBG(activities)
        }
    }
    private suspend fun insertAllBG(activities: List<activity>){
        withContext(Dispatchers.IO){
            activityDao.insertAll(activities)
        }
    }
    fun getExerciseCount()=
        runBlocking {
            return@runBlocking withContext(Dispatchers.IO) {
                return@withContext exerciseDao.getExeCount()
            }
        }
    fun getExersiceList()= runBlocking {
        return@runBlocking withContext(Dispatchers.IO) {
            return@withContext exerciseDao.getExercisesList()
        }
    }
    fun getRelatedActivitiesN(exeId:Int,N:Int)= runBlocking {
        return@runBlocking withContext(Dispatchers.IO) {
            return@withContext activityDao.getRelatedActivitiesN(exeId,N)
        }
    }
    fun getExercise(exeId:Int)= runBlocking {
        return@runBlocking withContext(Dispatchers.IO) {
            return@withContext exerciseDao.getExe(exeId)
        }
    }
    fun removeActivity(exeActivity:activity)= runBlocking {
        return@runBlocking withContext(Dispatchers.IO) {
            return@withContext activityDao.delete(exeActivity)
        }
    }
    fun updateActivity(activity:activity)=
        launch {
            updateActivityBG(activity)
        }
    suspend fun updateActivityBG(activity:activity){
        withContext(Dispatchers.IO)
        {
            activityDao.update(activity)
        }
    }
    fun clean_all_data()= runBlocking  {
        withContext(Dispatchers.IO){
            exerciseDao.deleteEverything()
        }
    }
}