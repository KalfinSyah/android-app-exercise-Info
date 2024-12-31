package com.example.exerciseinfoapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exerciseinfoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val listExercise = ArrayList<Exercise>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //  melakukan binding view agar tidak perlu mendeklarasikan setiap view
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set recyclerview menjadi fixed size
        binding.rvMain.setHasFixedSize(true)

        // menambahkan semua data yang diperlukan kedalam listExercise dan menampilkannya
        listExercise.addAll(getListExercise())
        showRecyclerList()
    }

    // fungsi ini adalah untuk menampilkan menu navbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // fungsi ini adalah untuk mendeteksi ketika menu (about me) diklik
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_me -> {
                val intent = Intent(this@MainActivity, AboutMeActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // fungsi ini adalah me-return sebauh array list exercise
    private fun getListExercise(): ArrayList<Exercise> {
        // mengambil semua data dari resources
        val dataExerciseName = resources.getStringArray(R.array.data_exercise_name)
        val dataExerciseDesc = resources.getStringArray(R.array.data_exercise_desc)
        val dataExerciseIcon = resources.obtainTypedArray(R.array.data_exercise_icon)
        val dataExerciseHowImg = resources.obtainTypedArray(R.array.data_exercise_detail_img)
        val dataExerciseDifficulty = resources.getStringArray(R.array.data_exercise_difficulty)
        val dataExerciseTargetMuscle = resources.getStringArray(R.array.data_exercise_target_muscle)
        val dataExerciseRisk = resources.getStringArray(R.array.data_exercise_risk)
        val dataExerciseHowToDoIt = resources.getStringArray(R.array.data_exercise_how_to_do_it)

        // membuat buffer untuk menampung data dari resources
        val listExerciseBuffer = ArrayList<Exercise>()

        // memasukkan data ke buffer denegan looping sebanyak jumlah item dari dataExerciseName
        for (i in dataExerciseName.indices) {
            val exercise = Exercise(
                dataExerciseName[i],
                dataExerciseDesc[i],
                dataExerciseIcon.getResourceId(i, -1),
                dataExerciseHowImg.getResourceId(i, -1),
                dataExerciseDifficulty[i],
                dataExerciseTargetMuscle[i],
                dataExerciseRisk[i],
                dataExerciseHowToDoIt[i])
            listExerciseBuffer.add(exercise)
        }

        dataExerciseIcon.recycle()
        dataExerciseHowImg.recycle()
        return listExerciseBuffer
    }

    // fungsi ini adalah untuk set layout manager yang ingin dipakai dan juga set adapter
    private fun showRecyclerList() {
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        val listExerciseAdapter = ListExerciseAdapter(listExercise)
        binding.rvMain.adapter = listExerciseAdapter
    }
}