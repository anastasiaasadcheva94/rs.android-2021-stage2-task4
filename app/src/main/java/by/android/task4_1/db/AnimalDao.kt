package by.android.task4_1.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AnimalDao {

    @Query("SELECT * FROM animals_table")
    suspend fun getAllAnimal(): List<AnimalEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(animalEntity: AnimalEntity)

    @Query("DELETE FROM animals_table")
    fun deleteAll()

    @Query("SELECT * FROM animals_table ORDER BY name ASC")
    suspend fun getFilteredListByName(): List<AnimalEntity>

    @Query("SELECT * FROM animals_table ORDER BY age")
    suspend fun getFilteredListByAge(): List<AnimalEntity>

    @Query("SELECT * FROM animals_table ORDER BY breed ASC")
    suspend fun getFilteredListByBreed(): List<AnimalEntity>
}