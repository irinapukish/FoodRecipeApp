package com.foodreceipeapp.shared.data.plan.remote

import com.foodreceipeapp.model.MealsPlan

interface MealPlanDataSource {
    suspend fun getMealsPlan(): MealsPlan
}
