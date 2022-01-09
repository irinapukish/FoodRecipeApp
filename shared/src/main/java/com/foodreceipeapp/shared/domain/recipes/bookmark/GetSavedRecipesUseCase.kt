package com.foodreceipeapp.shared.domain.recipes.bookmark

import com.foodreceipeapp.model.RecipesItem
import com.foodreceipeapp.shared.data.db.datastore.RecipesLocalDataStore
import com.foodreceipeapp.shared.di.IoDispatcher
import com.foodreceipeapp.shared.domain.FlowUseCase
import com.foodreceipeapp.shared.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedRecipesUseCase @Inject constructor(
    private val dataStore: RecipesLocalDataStore,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, List<RecipesItem>>(ioDispatcher) {
    override fun execute(parameters: Unit): Flow<Result<List<RecipesItem>>> =
        dataStore.getRecipes()
}
