package com.foodreceipeapp.shared.data.db.datastore

import com.foodreceipeapp.model.RecipesItem
import com.foodreceipeapp.shared.result.Result
import kotlinx.coroutines.flow.Flow

interface RecipesLocalDataStore {
    suspend fun saveRecipe(recipesItem: RecipesItem)
    fun getRecipes(): Flow<Result<List<RecipesItem>>>
    suspend fun getRecipeById(recipeId: Int?): RecipesItem?
    suspend fun deleteRecipe(recipeId: Int?)
    suspend fun isRecipeSaved(recipeId: Int?): Boolean
}
