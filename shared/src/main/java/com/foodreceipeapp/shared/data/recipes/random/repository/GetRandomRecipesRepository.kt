package com.foodreceipeapp.shared.data.recipes.random.repository

import com.foodreceipeapp.model.Recipe
import com.foodreceipeapp.shared.data.recipes.random.remote.RandomRecipesRemoteDataSource

class GetRandomRecipesRepository(
    private val randomRecipesRemoteDataSource: RandomRecipesRemoteDataSource
) : RandomRecipesRepository {
    override suspend fun getRandomRecipes(tags: String?, number: Int?): List<Recipe> =
        randomRecipesRemoteDataSource.getRandomRecipes(tags, number).recipes
}
