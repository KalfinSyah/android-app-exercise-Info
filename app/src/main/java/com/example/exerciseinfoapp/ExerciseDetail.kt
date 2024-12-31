package com.example.exerciseinfoapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.exerciseinfoapp.databinding.ActivityExerciseDetailBinding

class ExerciseDetail : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityExerciseDetailBinding

    // deklarasi konstanta untuk intent
    companion object {
        const val EXTRA_EXERCISE = "extra_exercise"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //  melakukan binding view agar tidak perlu mendeklarasikan setiap view
        binding = ActivityExerciseDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Exercise Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // mengambil data dari intent
        val exerciseData = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_EXERCISE, Exercise::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_EXERCISE)
        }
        if (exerciseData != null) {
            // menampilkan data ke view berdasarkan data yang diterima
            binding.imgExerciseOnDetailSection.setImageResource(exerciseData.icon)
            binding.tvNameExerciseOnDetailActivity.text = exerciseData.name
            binding.tvDescExerciseOnDetail.text = exerciseData.desc
            binding.ivExerciseDetailHow.setImageResource(exerciseData.howImg)
            binding.tvDifficulty.text = exerciseData.difficulty
            binding.tvTargetMuscle.text = exerciseData.targetMuscle
            binding.tvRisk.text = exerciseData.risk
            binding.tvHowTo.text = exerciseData.howToDoIt
        }

        // menambahkan listener untuk button share
        binding.actionShare.setOnClickListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(p0: View?) {
        if (p0?.id == R.id.action_share) {
            // membuat intent untuk share
            val sendIntent = Intent(Intent.ACTION_SEND)

            // menentukan tipe data yang akan di share
            sendIntent.type = "text/plain"

            // menambahkan data yang akan di share
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "description")
            sendIntent.putExtra(Intent.EXTRA_TEXT, "link app")

            // menjalankan intent
            startActivity(Intent.createChooser(sendIntent, "Share via"))
        }
    }
}