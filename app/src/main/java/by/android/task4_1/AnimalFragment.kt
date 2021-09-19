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
import by.android.task4_1.interfaces.ButtonListener
import java.io.Serializable


private const val ARG_PARAM1 = "param1"


class AnimalFragment : Fragment() {
    private lateinit var binding: FragmentAnimalBinding

    private lateinit var buttonListener: ButtonListener

    // TODO: Rename and change types of parameters
    private var param1: Serializable? = null

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
            param1 = it.getSerializable(ARG_PARAM1)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnimalBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)

        initButton()
//        initDB()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar2.title = "Animal"
    }

//    private fun initDB(){
//        val db = context?.let { AnimalRoomDatabase.getDatabase(it.applicationContext) }
//
//        if (param1 != null){
//            val animalList = listOf(param1)
//            Toast.makeText(activity, "$animalList", Toast.LENGTH_SHORT).show()
////            binding.recyclerView.adapter = AnimalAdapter(animalList)
//        } else {
//            GlobalScope.launch(Dispatchers.IO) {
//                val animalList = db?.animalDao()?.getAllAnimal()
//                withContext(Dispatchers.Main) {
//                    binding.recyclerView.adapter = animalList?.let { AnimalAdapter(it) }
//                }
//            }
//        }
//    }

    private fun initButton(){
        binding.actionFilter.setOnClickListener {
            buttonListener.second(
                    SortByFragment.newInstance()
            )
        }

        binding.floatingActionButton.setOnClickListener {
            buttonListener.second(
                    AnimalNewAddFragment.newInstance()
            )
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                AnimalFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_PARAM1, param1)
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