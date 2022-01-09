package com.foodreceipeapp.shared.data.plan.repository

import com.foodreceipeapp.model.MealsPlan

interface MealPlanRepository {
    suspend fun getMealsPlan(): MealsPlan
}
