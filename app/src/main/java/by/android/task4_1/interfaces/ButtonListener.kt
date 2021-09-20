package by.android.task4_1.interfaces

import androidx.fragment.app.Fragment

interface ButtonListener {
    fun second (nextFragment:Fragment)

    fun filterByName()

    fun filterByAge()

    fun filterByBreed()

}