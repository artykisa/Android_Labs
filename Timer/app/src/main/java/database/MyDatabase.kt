package database

import Interfaces.ActivityDao
import Interfaces.ExerciseDao
import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Database(entities = [Exercise::class, activity::class],version = 9)
abstract class MyDatabase: RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun activityDao(): ActivityDao
    companion object{
        private var instance: MyDatabase?=null
        @Synchronized
        fun getInstance(ctx: Context): MyDatabase {
            if(instance ==null)
                instance = Room.databaseBuilder(
                    ctx.applicationContext, MyDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()
            return instance!!
        }
        private val roomCallback=object :Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }
    }
    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("Not yet implemented")
    }
    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("Not yet implemented")
    }
    override fun clearAllTables() {
        TODO("Not yet implemented")
    }
}