package com.foodreceipeapp.shared.domain.location

import com.foodreceipeapp.model.LocationModel
import com.foodreceipeapp.shared.data.location.repository.ILocationRepository
import com.foodreceipeapp.shared.di.IoDispatcher
import com.foodreceipeapp.shared.domain.FlowUseCase
import com.foodreceipeapp.shared.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Get current user location.
 */
class GetCurrentLocationUseCase @Inject constructor(
    private val locationRepository: ILocationRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, LocationModel>(ioDispatcher) {

    override fun execute(parameters: Unit): Flow<Result<LocationModel>> {
        return locationRepository.getCurrentLocation()
    }
}
