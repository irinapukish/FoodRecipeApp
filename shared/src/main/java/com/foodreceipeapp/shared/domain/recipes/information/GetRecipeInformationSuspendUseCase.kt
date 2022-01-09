package com.foodreceipeapp.shared.domain.recipes.information

import com.foodreceipeapp.model.RecipesItem
import com.foodreceipeapp.shared.data.recipes.info.repository.RecipeInformationRepository
import com.foodreceipeapp.shared.di.IoDispatcher
import com.foodreceipeapp.shared.domain.SuspendUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetRecipeInformationSuspendUseCase @Inject constructor(
    private val recipeInformationRepository: RecipeInformationRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : SuspendUseCase<Int, RecipesItem>(ioDispatcher) {
    override suspend fun execute(parameters: Int): RecipesItem =
        recipeInformationRepository.getRecipeInformation(parameters)
}
