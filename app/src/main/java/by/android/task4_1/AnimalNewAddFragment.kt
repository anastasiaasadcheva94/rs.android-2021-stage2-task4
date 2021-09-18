package by.android.task4_1

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import by.android.task4_1.databinding.FragmentAnimalAddBinding
import by.android.task4_1.databinding.FragmentAnimalBinding
import by.android.task4_1.db.AnimalEntity
import by.android.task4_1.db.AnimalRoomDatabase
import by.android.task4_1.interfaces.ButtonListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AnimalAddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AnimalAddFragment : Fragment() {
    private var _binding: FragmentAnimalAddBinding? = null
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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAnimalAddBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.root.title = "Add new animal"

        var id = 0
        var name = ""
        var age = ""
        var breed = ""

        val db = AnimalRoomDatabase.getDatabase(AnimalFragment())

        binding.addButton.setOnClickListener {
            if (binding.editName.text.isNotEmpty() && binding.editAge.text.isNotEmpty() && binding.editBreed.text.isNotEmpty()){
                name = binding.editName.text.toString()
                age = binding.editAge.text.toString()
                breed = binding.editBreed.text.toString()
                val animalEntity = AnimalEntity(id, name, age, breed)

                GlobalScope.launch(Dispatchers.IO) {
                    db.animalDao().insert(animalEntity)
                }

                buttonListener.second(
                        AnimalFragment.newInstance(
                                "",
                                "")
                )
            } else {
                Toast.makeText(activity, "Edit values", Toast.LENGTH_SHORT).show()
          }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AnimalAddFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AnimalAddFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}