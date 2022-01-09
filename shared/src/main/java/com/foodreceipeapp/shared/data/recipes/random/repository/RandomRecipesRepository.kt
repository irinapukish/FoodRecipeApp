package com.foodreceipeapp.shared.data.recipes.random.repository

import com.foodreceipeapp.model.Recipe

interface RandomRecipesRepository {

    suspend fun getRandomRecipes(
        tags: String?,
        number: Int?
    ): List<Recipe>
}
