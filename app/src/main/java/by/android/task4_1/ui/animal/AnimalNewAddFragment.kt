package by.android.task4_1.ui.animal

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.android.task4_1.databinding.FragmentAnimalAddBinding
import by.android.task4_1.db.AnimalEntity
import by.android.task4_1.db.AnimalRoomDatabase
import by.android.task4_1.interfaces.ButtonListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AnimalNewAddFragment : Fragment() {
    private lateinit var binding: FragmentAnimalAddBinding

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
        binding = FragmentAnimalAddBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.root.title = "Add new animal"

        val id = 0
        var name: String
        var age: Int
        var breed: String

        binding.addButton.setOnClickListener {
            if (binding.editName.text.isNotEmpty() && binding.editAge.text.isNotEmpty() && binding.editBreed.text.isNotEmpty()) {
                name = binding.editName.text.toString()
                age = binding.editAge.text.toString().toInt()
                breed = binding.editBreed.text.toString()
                val animalEntity = AnimalEntity(id, name, age, breed)

                val db = context?.let { it1 -> AnimalRoomDatabase.getDatabase(it1.applicationContext) }
                GlobalScope.launch(Dispatchers.IO) {
                    db?.animalDao()?.insert(animalEntity)
                }

                buttonListener.second(
                        AnimalFragment()
                )
            } else {
                Toast.makeText(activity, "Edit values", Toast.LENGTH_SHORT).show()
            }
        }
    }
}