package by.android.task4_1.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import by.android.task4_1.AnimalFragment

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [AnimalEntity::class], version = 1, exportSchema = true)
abstract class AnimalRoomDatabase : RoomDatabase() {

    abstract fun animalDao(): AnimalDao

    companion object {
          @Volatile
        private var INSTANCE: AnimalRoomDatabase? = null

        fun getDatabase(context: AnimalFragment): AnimalRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.requireContext(),
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