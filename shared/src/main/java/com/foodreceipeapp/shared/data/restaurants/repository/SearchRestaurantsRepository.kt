package com.foodreceipeapp.shared.data.restaurants.repository

import com.foodreceipeapp.model.VenuesItem
import com.foodreceipeapp.shared.data.restaurants.remote.ISearchRestaurantsDataSource
import com.foodreceipeapp.shared.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRestaurantsRepository @Inject constructor(
    private val restaurantsSearchDataSource: ISearchRestaurantsDataSource
) : ISearchRestaurantsRepository {
    override fun search(
        latLng: String,
        version: String,
        radius: Int?,
        limit: Int?
    ): Flow<Result<List<VenuesItem>>> {
        return flow {
            val items = restaurantsSearchDataSource.search(
                latLng, version, radius, limit
            ).response?.venues
                ?: emptyList()
            emit(Result.Success(items))
        }
    }
}
