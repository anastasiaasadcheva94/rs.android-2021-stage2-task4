package by.android.task4_1.ui.animal

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.android.task4_1.R
import by.android.task4_1.databinding.FragmentAnimalBinding
import by.android.task4_1.db.AnimalEntity
import by.android.task4_1.interfaces.ButtonListener
import by.android.task4_1.ui.MainActivity
import by.android.task4_1.ui.animal.adapter.AnimalAdapter
import by.android.task4_1.ui.sorting.SortByFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AnimalFragment : Fragment() {
    private lateinit var binding: FragmentAnimalBinding
    private val animalAdapter: AnimalAdapter = AnimalAdapter(arrayListOf())
    private lateinit var buttonListener: ButtonListener
    private var sortedlist = listOf<AnimalEntity>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            buttonListener = context as ButtonListener
        } catch (e: Exception) {
            throw RuntimeException("$context must implement ButtonListener")
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnimalBinding.inflate(inflater, container, false)
        binding.toolbar.title = getString(R.string.animal)
        initButton()
        initUI()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).initDB()
        initDB()
    }

    private fun initUI() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = animalAdapter
        }
    }

    private fun initDB() {
        GlobalScope.launch(Dispatchers.Main) {
            delay(30)
            (activity as MainActivity).returnFilter().also {
                if (it != null) {
                    sortedlist = it
                }
            }
            getUpdatedAnimalList(sortedlist)
        }
    }

    private fun initButton() {
        binding.actionFilter.setOnClickListener {
            buttonListener.openNewFragment(
                    SortByFragment()
            )
        }

        binding.floatingActionButton.setOnClickListener {
            buttonListener.openNewFragment(
                    AddNewAnimalFragment()
            )
        }
    }

    private fun getUpdatedAnimalList(list: List<AnimalEntity>) {
        animalAdapter.apply {
            updateAnimalList(list)
            notifyDataSetChanged()
        }
    }
}