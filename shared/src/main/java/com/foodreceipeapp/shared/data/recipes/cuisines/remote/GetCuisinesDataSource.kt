package com.foodreceipeapp.shared.data.recipes.cuisines.remote

import com.foodreceipeapp.model.CuisineItem

interface GetCuisinesDataSource {
    suspend fun getAvailableCuisines(): List<CuisineItem>
}
