package com.foodreceipeapp.shared.data.restaurants.repository

import com.foodreceipeapp.model.VenuesItem
import com.foodreceipeapp.shared.result.Result
import kotlinx.coroutines.flow.Flow

interface ISearchRestaurantsRepository {
    fun search(
        latLng: String,
        version: String,
        radius: Int?,
        limit: Int?
    ): Flow<Result<List<VenuesItem>>>
}
