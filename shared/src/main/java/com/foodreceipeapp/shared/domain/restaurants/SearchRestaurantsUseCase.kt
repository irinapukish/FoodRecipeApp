package com.foodreceipeapp.shared.domain.restaurants

import com.foodreceipeapp.model.VenuesItem
import com.foodreceipeapp.shared.data.restaurants.repository.ISearchRestaurantsRepository
import com.foodreceipeapp.shared.di.IoDispatcher
import com.foodreceipeapp.shared.domain.FlowUseCase
import com.foodreceipeapp.shared.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Search for the nearby restaurant using [Params] value.
 */
class SearchRestaurantsUseCase @Inject constructor(
    private val searchRepository: ISearchRestaurantsRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<SearchRestaurantsUseCase.Params, List<VenuesItem>>(ioDispatcher) {

    override fun execute(parameters: Params): Flow<Result<List<VenuesItem>>> =
        searchRepository.search(
            parameters.latLng,
            parameters.version,
            parameters.radius,
            parameters.limit
        )

    class Params private constructor(
        val latLng: String,
        val version: String,
        val radius: Int?,
        val limit: Int?
    ) {

        companion object {
            @JvmStatic
            fun create(
                latLng: String,
                version: String,
                radius: Int? = null,
                limit: Int? = null
            ): Params {
                return Params(latLng, version, radius, limit)
            }
        }
    }
}
