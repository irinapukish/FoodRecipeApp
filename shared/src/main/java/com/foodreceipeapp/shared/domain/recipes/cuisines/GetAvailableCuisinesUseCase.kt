package com.foodreceipeapp.shared.domain.recipes.cuisines

import com.foodreceipeapp.model.CuisineItem
import com.foodreceipeapp.shared.data.recipes.cuisines.repository.CuisinesRepository
import com.foodreceipeapp.shared.domain.UseCase
import com.foodreceipeapp.shared.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAvailableCuisinesUseCase @Inject constructor(
    private val cuisinesRepository: CuisinesRepository
) : UseCase<Unit, Flow<Result<List<CuisineItem>>>>() {

    override fun execute(parameters: Unit): Flow<Result<List<CuisineItem>>> =
        flow {
            try {
                val cuisines = cuisinesRepository.getAvailableCuisines()
                emit(Result.Success(cuisines))
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }
}
