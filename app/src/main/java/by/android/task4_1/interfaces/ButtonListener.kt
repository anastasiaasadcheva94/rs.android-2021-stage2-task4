package by.android.task4_1.interfaces

import androidx.fragment.app.Fragment

interface ButtonListener {
    fun openNewFragment(newFragment: Fragment)

    fun filterByName()

    fun filterByAge()

    fun filterByBreed()

}