package database

import androidx.room.Embedded
import androidx.room.Relation

data class ExerciseActivities (
    @Embedded val exercise: Exercise,
    @Relation(
        parentColumn = "id",
        entityColumn = "exeId"
    )
    val activities:List<activity>
)