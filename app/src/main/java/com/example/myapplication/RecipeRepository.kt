package com.example.myapplication

class RecipeRepository {
    private val recipes: ArrayList<Recipe> = ArrayList()

    fun addRecipe(recipe: Recipe) {
        recipes.add(recipe)
    }

    fun removeRecipe(recipe: Recipe):Boolean {
        return recipes.remove(recipe)
    }

    fun getAllRecipes(): List<Recipe> {
        return recipes.toList()
    }
}