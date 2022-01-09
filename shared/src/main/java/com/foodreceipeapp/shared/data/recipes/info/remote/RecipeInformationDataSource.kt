package com.foodreceipeapp.shared.data.recipes.info.remote

import com.foodreceipeapp.model.Recipe

interface RecipeInformationDataSource {
    suspend fun getRecipeInformation(
        id: Int?
    ): Recipe
}
