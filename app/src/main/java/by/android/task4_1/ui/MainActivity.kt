package by.android.task4_1.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import by.android.task4_1.AnimalFragment
import by.android.task4_1.R
import by.android.task4_1.databinding.ActivityMainBinding
import by.android.task4_1.db.AnimalEntity
import by.android.task4_1.db.AnimalRoomDatabase
import by.android.task4_1.interfaces.ButtonListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity(), ButtonListener {
    private lateinit var binding: ActivityMainBinding

    private var sortedList: List<AnimalEntity>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openFirstFragment()
    }


    private fun openFirstFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, AnimalFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun openSecondFragment(nextFragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, nextFragment)
                .addToBackStack(null)
                .commit()
    }

    override fun second(nextFragment: Fragment) {
        openSecondFragment(nextFragment)
    }

    override fun filterByName() {
        val db = AnimalRoomDatabase.getDatabase(this)
        GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main){
                sortedList = db.animalDao().getFilteredListByName()
                Log.d("TAG", "$sortedList")
                Toast.makeText(this@MainActivity, "click1", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun filterByAge() {
        val db = AnimalRoomDatabase.getDatabase(this)
        GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main){
                sortedList = db.animalDao().getFilteredListByAge()
                Log.d("TAG", "$sortedList")
                Toast.makeText(this@MainActivity, "click2", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun filterByBreed() {
        val db = AnimalRoomDatabase.getDatabase(this)
        GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main){
                sortedList = db.animalDao().getFilteredListByBreed()
                Log.d("TAG", "$sortedList")
                Toast.makeText(this@MainActivity, "click3", Toast.LENGTH_SHORT).show()
            }
        }
    }
}