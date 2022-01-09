package com.foodreceipeapp.shared.data.recipes.info.repository

import com.foodreceipeapp.model.RecipesItem

interface RecipeInformationRepository {
    suspend fun getRecipeInformation(
        id: Int?
    ): RecipesItem
}
