package com.foodreceipeapp.shared.data.restaurants.remote

import com.foodreceipeapp.model.VenuesResult

interface ISearchRestaurantsDataSource {

    suspend fun search(
        latLng: String,
        version: String,
        radius: Int?,
        limit: Int?
    ): VenuesResult
}
