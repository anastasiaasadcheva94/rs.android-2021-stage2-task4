package by.android.task4_1

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import by.android.task4_1.databinding.FragmentSortByBinding
import by.android.task4_1.db.AnimalEntity
import by.android.task4_1.db.AnimalRoomDatabase
import by.android.task4_1.interfaces.ButtonListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val ARG_PARAM1 = "param1"

class SortByFragment : Fragment() {
    private lateinit var binding: FragmentSortByBinding

    private lateinit var buttonListener: ButtonListener

    private var param1: String? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            buttonListener = context as ButtonListener
        } catch (e: Exception) {
            throw RuntimeException("$context must implement QuizFragmentListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentSortByBinding.inflate(inflater, container, false)
        binding.buttonName.setOnClickListener {
            buttonListener.sortByName()
            back()
        }
        binding.buttonAge.setOnClickListener {
            buttonListener.sortByAge()
            back()
        }
        binding.buttonBreed.setOnClickListener {
            buttonListener.sortByBreed()
            back()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.root.title = "Sort by"
    }

    private fun back() {
        buttonListener.second(
                AnimalFragment.newInstance()
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() = SortByFragment()
    }
}