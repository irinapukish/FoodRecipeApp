package com.foodreceipeapp.shared.data.recipes.info.remote

import com.foodreceipeapp.model.Recipe
import com.foodreceipeapp.shared.data.remote.DelishApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeInformationRemoteDataSource(
    private val api: DelishApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RecipeInformationDataSource {
    override suspend fun getRecipeInformation(id: Int?): Recipe =
        withContext(ioDispatcher) {
            api.getRecipeInformation(id = id)
        }
}
