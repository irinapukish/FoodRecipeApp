package com.foodreceipeapp.shared.data.recipes.ingredients.remote

import com.foodreceipeapp.model.IngredientItem

interface GetIngredientsDataSource {
    suspend fun getIngredients(): List<IngredientItem>
}
