package database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "activities_table",
    foreignKeys = [ForeignKey(onDelete = CASCADE,entity = Exercise::class,
        parentColumns = arrayOf("id"),childColumns = arrayOf("exeId")) ])
data class activity (
    var title:Int,
    var description:String,
    var duration: Int,
    var repitations:Int,
    var exeId:Int,
    var number:Int,
    var rest:Int,
    @PrimaryKey(autoGenerate = true) val id:Int?=null
)