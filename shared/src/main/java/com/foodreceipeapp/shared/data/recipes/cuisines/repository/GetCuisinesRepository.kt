package com.foodreceipeapp.shared.data.recipes.cuisines.repository

import com.foodreceipeapp.model.CuisineItem
import com.foodreceipeapp.shared.data.recipes.cuisines.remote.GetCuisinesDataSource
import javax.inject.Inject

class GetCuisinesRepository @Inject constructor(
    private val getCuisinesDataSource: GetCuisinesDataSource
) : CuisinesRepository {
    override suspend fun getAvailableCuisines(): List<CuisineItem> =
        getCuisinesDataSource.getAvailableCuisines()
}
