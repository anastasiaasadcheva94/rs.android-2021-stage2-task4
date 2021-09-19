package by.android.task4_1

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.android.task4_1.databinding.FragmentAnimalBinding
import by.android.task4_1.databinding.ItemRvBinding
import by.android.task4_1.db.AnimalEntity
import by.android.task4_1.db.AnimalRoomDatabase
import by.android.task4_1.interfaces.ButtonListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AnimalFragment : Fragment() {
    private var _binding: FragmentAnimalBinding? = null
    private val binding get() = _binding!!

    private lateinit var buttonListener: ButtonListener

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnimalBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)

        initButton()
        initDB()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar2.title = "Animal"
    }

    private fun initDB(){
        val db = AnimalRoomDatabase.getDatabase(this)

        GlobalScope.launch(Dispatchers.Main) {
            val animalList = db.animalDao().getAllAnimal()

//            var newAnimalList = mutableListOf(animalList)
//            newAnimalList.sortBy {  }

            binding.recyclerView.adapter = AnimalAdapter(animalList)
        }
    }


    private fun initButton(){
        binding.actionFilter.setOnClickListener {
            buttonListener.second(
                    SortByFragment.newInstance(
                            "",
                            "")
            )
        }

        binding.floatingActionButton.setOnClickListener {
            buttonListener.second(
                    AnimalAddFragment.newInstance(
                            "",
                            "")
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AnimalFragment.
         */
// TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                AnimalFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}



class AnimalAdapter(private var animals: List<AnimalEntity>) : RecyclerView.Adapter<AnimalAdapter.ViewHolder>() {
    private lateinit var binding: ItemRvBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = "Name: " + animals[position].name
        holder.age.text = "Age: " +
                animals[position].age

        holder.breed.text = "Breed: " + animals[position].breed
    }

    override fun getItemCount(): Int {
        return animals.size
    }

    class ViewHolder(binding: ItemRvBinding) : RecyclerView.ViewHolder(binding.root) {
        var name: TextView = binding.name
        var age: TextView = binding.age
        var breed: TextView = binding.breed
    }
}