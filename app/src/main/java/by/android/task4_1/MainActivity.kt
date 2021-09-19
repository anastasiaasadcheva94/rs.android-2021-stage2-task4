package by.android.task4_1

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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
        val animalFragment: Fragment = AnimalFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, animalFragment)
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

    override fun sortByName() {
        val db = AnimalRoomDatabase.getDatabase(this)
        GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main){
                sortedList = db.animalDao().getAlphabetizedName()
                Log.d("TAG", "$sortedList")
                Toast.makeText(this@MainActivity, "click1", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun sortByAge() {
        val db = AnimalRoomDatabase.getDatabase(this)
        GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main){
                sortedList = db.animalDao().getSortedAge()
                Log.d("TAG", "$sortedList")
                Toast.makeText(this@MainActivity, "click2", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun sortByBreed() {
        val db = AnimalRoomDatabase.getDatabase(this)
        GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main){
                sortedList = db.animalDao().getAlphabetizedBreed()
                Log.d("TAG", "$sortedList")
                Toast.makeText(this@MainActivity, "click3", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun addNewAnimal() {
//        GlobalScope.launch(Dispatchers.IO) {
//            db.animalDao().insert(animalEntity)
//        }
    }
}