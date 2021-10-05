package by.android.task4_1.ui.sorting

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.android.task4_1.R
import by.android.task4_1.ui.animal.AnimalFragment
import by.android.task4_1.databinding.FragmentSortByBinding
import by.android.task4_1.interfaces.ButtonListener


class SortByFragment : Fragment() {
    private lateinit var binding: FragmentSortByBinding

    private lateinit var buttonListener: ButtonListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            buttonListener = context as ButtonListener
        } catch (e: Exception) {
            throw RuntimeException("$context must implement ButtonListener")
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentSortByBinding.inflate(inflater, container, false)
        binding.buttonName.setOnClickListener {
            buttonListener.filterByName()
            back()
        }
        binding.buttonAge.setOnClickListener {
            buttonListener.filterByAge()
            back()
        }
        binding.buttonBreed.setOnClickListener {
            buttonListener.filterByBreed()
            back()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.root.title = getString(R.string.sort_by)
    }

    private fun back() {
        buttonListener.openNewFragment(
                AnimalFragment()
        )
    }
}