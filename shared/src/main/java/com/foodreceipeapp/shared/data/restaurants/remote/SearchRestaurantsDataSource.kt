package com.foodreceipeapp.shared.data.restaurants.remote

import com.foodreceipeapp.model.VenuesResult
import com.foodreceipeapp.shared.data.remote.DelishApi
import javax.inject.Inject

const val defaultRadius = 500
const val defaultLimit = 30

class SearchRestaurantsDataSource @Inject constructor(
    private val api: DelishApi
) : ISearchRestaurantsDataSource {
    override suspend fun search(
        latLng: String,
        version: String,
        radius: Int?,
        limit: Int?
    ): VenuesResult =
        api.search(
            latLng = latLng,
            version = version,
            radius = radius ?: defaultRadius,
            limit = limit ?: defaultLimit
        )
}
