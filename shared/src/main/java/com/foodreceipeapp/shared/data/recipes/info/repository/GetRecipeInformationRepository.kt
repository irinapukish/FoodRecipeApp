package com.foodreceipeapp.shared.data.recipes.info.repository

import com.foodreceipeapp.model.RecipesItem
import com.foodreceipeapp.model.toUiModel
import com.foodreceipeapp.shared.data.db.datastore.RecipesLocalDataStore
import com.foodreceipeapp.shared.data.recipes.info.remote.RecipeInformationDataSource

class GetRecipeInformationRepository(
    private val recipeInformationDataSource: RecipeInformationDataSource,
    private val recipesLocalDataStore: RecipesLocalDataStore
) : RecipeInformationRepository {
    override suspend fun getRecipeInformation(recipeId: Int?): RecipesItem {
        return recipesLocalDataStore.getRecipeById(recipeId)
            ?: recipeInformationDataSource.getRecipeInformation(recipeId).toUiModel()
    }
}
