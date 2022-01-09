package com.foodreceipeapp.shared.data.location.repository

import com.foodreceipeapp.model.LocationModel
import kotlinx.coroutines.flow.Flow
import com.foodreceipeapp.shared.result.Result

interface ILocationRepository {

    fun getCurrentLocation(): Flow<Result<LocationModel>>
}
