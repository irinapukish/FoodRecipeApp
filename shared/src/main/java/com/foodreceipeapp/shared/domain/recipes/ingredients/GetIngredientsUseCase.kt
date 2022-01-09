package com.foodreceipeapp.shared.domain.recipes.ingredients

import com.foodreceipeapp.model.IngredientItem
import com.foodreceipeapp.shared.data.recipes.ingredients.repository.IngredientsRepository
import com.foodreceipeapp.shared.domain.UseCase
import com.foodreceipeapp.shared.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetIngredientsUseCase @Inject constructor(
    private val ingredientsRepository: IngredientsRepository
) : UseCase<Unit, Flow<Result<List<IngredientItem>>>>() {

    override fun execute(parameters: Unit): Flow<Result<List<IngredientItem>>> =
        flow {
            try {
                val ingredients = ingredientsRepository.getIngredients()
                emit(Result.Success(ingredients))
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }
}
