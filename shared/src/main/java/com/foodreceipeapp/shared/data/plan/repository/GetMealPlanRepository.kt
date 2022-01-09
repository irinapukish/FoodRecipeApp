package com.foodreceipeapp.shared.data.plan.repository

import com.foodreceipeapp.model.MealsPlan
import com.foodreceipeapp.shared.data.plan.remote.MealPlanDataSource
import javax.inject.Inject

class GetMealPlanRepository @Inject constructor(
    private val mealPlanRemoteDataSource: MealPlanDataSource
) : MealPlanRepository {
    override suspend fun getMealsPlan(): MealsPlan = mealPlanRemoteDataSource.getMealsPlan()
}
