package com.foodreceipeapp.shared.data.recipes.ingredients.remote

import com.foodreceipeapp.model.IngredientItem
import com.foodreceipeapp.shared.data.remote.DelishApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetIngredientsRemoteDataSource @Inject constructor(
    private val api: DelishApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : GetIngredientsDataSource {

    override suspend fun getIngredients(): List<IngredientItem> =
        withContext(ioDispatcher) {
            api.getIngredients()
        }
}
