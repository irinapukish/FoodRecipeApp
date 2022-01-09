package com.foodreceipeapp.shared.domain.recipes.bookmark

import com.foodreceipeapp.model.RecipesItem
import com.foodreceipeapp.shared.data.db.datastore.RecipesLocalDataStore
import com.foodreceipeapp.shared.di.IoDispatcher
import com.foodreceipeapp.shared.domain.SuspendUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SaveRecipeSuspendUseCase @Inject constructor(
    private val dataStore: RecipesLocalDataStore,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : SuspendUseCase<RecipesItem, Unit>(ioDispatcher) {
    override suspend fun execute(
        parameters: RecipesItem
    ) = dataStore.saveRecipe(parameters)
}
