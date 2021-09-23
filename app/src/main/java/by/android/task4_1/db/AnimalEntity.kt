package by.android.task4_1.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "animals_table")
data class AnimalEntity(
        @PrimaryKey(autoGenerate = true) var id: Int,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "age") val age: String,
        @ColumnInfo(name = "breed") val breed: String
)
