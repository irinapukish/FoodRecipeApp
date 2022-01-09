package com.foodreceipeapp.shared.data.recipes.ingredients.repository

import com.foodreceipeapp.model.IngredientItem
import com.foodreceipeapp.shared.data.recipes.ingredients.remote.GetIngredientsDataSource
import javax.inject.Inject

class GetIngredientsRepository @Inject constructor(
    private val getIngredientsDataSource: GetIngredientsDataSource
) : IngredientsRepository {
    override suspend fun getIngredients(): List<IngredientItem> =
        getIngredientsDataSource.getIngredients()
}
