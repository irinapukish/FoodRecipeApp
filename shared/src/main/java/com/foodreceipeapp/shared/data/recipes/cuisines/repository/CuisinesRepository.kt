package com.foodreceipeapp.shared.data.recipes.cuisines.repository

import com.foodreceipeapp.model.CuisineItem

interface CuisinesRepository {
    suspend fun getAvailableCuisines(): List<CuisineItem>
}
