package database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context,
    DB_NAME, null,
    DB_VERSION
) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME (" +
                ID + " INTEGER PRIMARY KEY," +
                NAME + " TEXT," + DESC + " TEXT," +
                COMPLETED + " TEXT);"
        db.execSQL(CREATE_TABLE)
        val CREATE_TABLE2 = "CREATE TABLE $TABLE_NAME2 (" +
                ID + " INTEGER PRIMARY KEY," +
                NAME + " TEXT," + DESC + " TEXT," +
                COMPLETED + " TEXT);"
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME
        db.execSQL(DROP_TABLE)
        onCreate(db)
    }

    fun addTask(tasks: TimerSeq): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAME, tasks.name)
        values.put(DESC, tasks.desc)
        values.put(COMPLETED, tasks.completed)
        val _success = db.insert(TABLE_NAME, null, values)
        db.close()
        return (Integer.parseInt("$_success") != -1)
    }

    fun getTask(_id: Int): TimerSeq {
        val tasks = TimerSeq()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM $TABLE_NAME WHERE $ID = $_id"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (cursor.moveToNext()) {
                tasks.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(
                    ID
                )))
                tasks.name = cursor.getString(cursor.getColumnIndex(NAME))
                tasks.desc = cursor.getString(cursor.getColumnIndex(DESC))
                tasks.completed = cursor.getInt(cursor.getColumnIndex(COMPLETED))
            }
        }
        cursor.close()
        return tasks
    }

    val task: List<TimerSeq>
        get() {
            val taskList = ArrayList<TimerSeq>()
            val db = writableDatabase
            val selectQuery = "SELECT  * FROM $TABLE_NAME"
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor != null) {
                cursor.moveToFirst()
                while (cursor.moveToNext()) {
                    val tasks = TimerSeq()
                    tasks.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(
                        ID
                    )))
                    tasks.name = cursor.getString(cursor.getColumnIndex(NAME))
                    tasks.desc = cursor.getString(cursor.getColumnIndex(DESC))
                    tasks.completed = cursor.getInt(cursor.getColumnIndex(COMPLETED))
                    taskList.add(tasks)
                }
            }
            cursor.close()
            return taskList
        }

    fun updateTask(tasks: TimerSeq): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAME, tasks.name)
        values.put(DESC, tasks.desc)
        values.put(COMPLETED, tasks.completed)
        val _success = db.update(TABLE_NAME, values, ID + "=?", arrayOf(tasks.id.toString())).toLong()
        db.close()
        return Integer.parseInt("$_success") != -1
    }

    fun deleteTask(_id: Int): Boolean {
        val db = this.writableDatabase
        val _success = db.delete(TABLE_NAME, ID + "=?", arrayOf(_id.toString())).toLong()
        db.close()
        return Integer.parseInt("$_success") != -1
    }

    companion object {

        private val DB_VERSION = 1
        private val DB_NAME = "TimerDb"
        private val TABLE_NAME = "exercise_table"
        private val TABLE_NAME2 = "activities_table"
        private val ID = "Id"
        private val NAME = "Name"
        private val DESC = "Desc"
        private val COMPLETED = "Completed"
    }
}