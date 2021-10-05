package by.android.task4_1.ui.animal.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.android.task4_1.databinding.ItemRvBinding
import by.android.task4_1.db.AnimalEntity

class AnimalAdapter(private val animals: ArrayList<AnimalEntity>) : RecyclerView.Adapter<AnimalAdapter.ViewHolder>() {
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

    fun updateAnimalList(list: List<AnimalEntity>) {
        this.animals.apply {
            clear()
            addAll(list)
        }
    }

    class ViewHolder(binding: ItemRvBinding) : RecyclerView.ViewHolder(binding.root) {
        var name: TextView = binding.name
        var age: TextView = binding.age
        var breed: TextView = binding.breed
    }
}