package com.foodreceipeapp.shared.data.recipes.ingredients.repository

import com.foodreceipeapp.model.IngredientItem

interface IngredientsRepository {
    suspend fun getIngredients(): List<IngredientItem>
}
