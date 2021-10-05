package by.android.task4_1.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import by.android.task4_1.R
import by.android.task4_1.databinding.ActivityMainBinding
import by.android.task4_1.db.AnimalEntity
import by.android.task4_1.db.AnimalRoomDatabase
import by.android.task4_1.interfaces.ButtonListener
import by.android.task4_1.ui.animal.AnimalFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity(), ButtonListener {
    private lateinit var binding: ActivityMainBinding
    private var sortedList: List<AnimalEntity>? = null
    lateinit var db: AnimalRoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openNewFragment(AnimalFragment())
        initDB()
    }

    fun initDB() {
        db = AnimalRoomDatabase.getDatabase(applicationContext)
        GlobalScope.launch(Dispatchers.IO) {
            sortedList = db.animalDao().getAllAnimal()
        }
    }

    override fun openNewFragment(newFragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, newFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun filterByName() {
        GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                sortedList = db.animalDao().getFilteredListByName()
            }
        }
    }

    override fun filterByAge() {
        GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                sortedList = db.animalDao().getFilteredListByAge()
            }
        }
    }

    override fun filterByBreed() {
        GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                sortedList = db.animalDao().getFilteredListByBreed()
            }
        }
    }

    fun returnFilter(): List<AnimalEntity>? {
        return sortedList
    }

}