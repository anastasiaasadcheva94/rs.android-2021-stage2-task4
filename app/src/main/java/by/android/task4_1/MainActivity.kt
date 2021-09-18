package by.android.task4_1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import by.android.task4_1.databinding.ActivityMainBinding
import by.android.task4_1.interfaces.ButtonListener


class MainActivity : AppCompatActivity(), ButtonListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        openFirstFragment()
    }

    private fun openFirstFragment() {
        val animalFragment: Fragment = AnimalFragment.newInstance(
            "",
            ""
        )
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
}