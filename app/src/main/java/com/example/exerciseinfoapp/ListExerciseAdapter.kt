package com.example.exerciseinfoapp

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.exerciseinfoapp.databinding.ItemRowExerciseBinding

// membuat adapter untuk recyclerview
class ListExerciseAdapter(private val listExercise: ArrayList<Exercise>): RecyclerView.Adapter<ListExerciseAdapter.ListViewHolder>() {
    // membuat view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowExerciseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    // mendapatkan atau get jumlah item yang ada pada listExercise
    override fun getItemCount(): Int = listExercise.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) = holder.bind(listExercise[position])

    class ListViewHolder(var binding: ItemRowExerciseBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(exercise: Exercise) {
            binding.imgExercise.setImageResource(exercise.icon)
            binding.tvNameExercise.text = exercise.name
            binding.tvDescExercise.text = exercise.desc

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, ExerciseDetail::class.java)
                intent.putExtra("extra_exercise", exercise)

//                itemView.context.startActivity(
//                    intent,
//                    ActivityOptionsCompat.makeSceneTransitionAnimation(itemView.context as Activity).toBundle()
//                )

                val optionsCompat: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        itemView.context as Activity,
                        androidx.core.util.Pair(binding.imgExercise, "profile"),
                        androidx.core.util.Pair(binding.tvNameExercise, "name"),
                        androidx.core.util.Pair(binding.tvDescExercise, "desc"),
                    )

                itemView.context.startActivity(intent, optionsCompat.toBundle())
            }
        }
    }
}