package com.foodreceipeapp.shared.domain.plan

import com.foodreceipeapp.model.DayMeal
import com.foodreceipeapp.shared.data.plan.repository.GetMealPlanRepository
import com.foodreceipeapp.shared.di.IoDispatcher
import com.foodreceipeapp.shared.domain.SuspendUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetMealPlanUseCase @Inject constructor(
    private val getMealPlanRepository: GetMealPlanRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : SuspendUseCase<Unit, List<DayMeal?>>(ioDispatcher) {
    override suspend fun execute(parameters: Unit): List<DayMeal?> {
        val mealPlan = getMealPlanRepository.getMealsPlan().week
        return listOf(
            mealPlan?.saturday,
            mealPlan?.sunday,
            mealPlan?.monday,
            mealPlan?.tuesday,
            mealPlan?.wednesday,
            mealPlan?.thursday,
            mealPlan?.friday
        )
    }
}
