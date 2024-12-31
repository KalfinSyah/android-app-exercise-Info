package com.example.exerciseinfoapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Exercise(
    val name: String,
    val desc: String,
    val icon: Int,
    val howImg: Int,
    val difficulty: String,
    val targetMuscle: String,
    val risk: String,
    val howToDoIt: String
): Parcelable
