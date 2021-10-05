package by.android.task4_1.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AnimalEntity::class], version = 1, exportSchema = true)
abstract class AnimalRoomDatabase : RoomDatabase() {

    abstract fun animalDao(): AnimalDao

    companion object {
        @Volatile
        private var INSTANCE: AnimalRoomDatabase? = null

        fun getDatabase(context: Context): AnimalRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AnimalRoomDatabase::class.java,
                        "animal_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}