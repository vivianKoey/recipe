package com.example.myapplication

import android.net.Uri
import androidx.compose.runtime.MutableState

class RecipeApp {
    private val recipeRepository: RecipeRepository = RecipeRepository()

    fun addNewRecipe(
        name: String,
        type:String, ingredients: String,
        instructions: String,
    ) {
        val newRecipe = Recipe(name,type, ingredients, instructions)
        recipeRepository.addRecipe(newRecipe)
    }

    fun removeRecipe(recipe: Recipe):Boolean {
        return recipeRepository.removeRecipe(recipe)
    }

    fun getAllRecipes(): List<Recipe> {
        return recipeRepository.getAllRecipes()
    }
}