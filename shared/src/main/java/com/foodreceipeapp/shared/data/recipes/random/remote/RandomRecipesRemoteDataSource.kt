package com.foodreceipeapp.shared.data.recipes.random.remote

import com.foodreceipeapp.model.Recipes

interface RandomRecipesRemoteDataSource {

    suspend fun getRandomRecipes(
        tags: String?,
        number: Int?
    ): Recipes
}
