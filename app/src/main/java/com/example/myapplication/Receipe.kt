package com.example.myapplication

import android.net.Uri
import androidx.compose.runtime.MutableState
import java.io.Serializable

data class Recipe(
    val name: String,
    val type:String,
    val ingredients: String,
    val instructions:String,
) : Serializable

