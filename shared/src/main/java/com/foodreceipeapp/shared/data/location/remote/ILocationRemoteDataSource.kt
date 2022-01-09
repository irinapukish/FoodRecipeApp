package com.foodreceipeapp.shared.data.location.remote

import com.foodreceipeapp.model.LocationModel
import kotlinx.coroutines.flow.Flow

interface ILocationRemoteDataSource {

    fun getCurrentLocation(): Flow<LocationModel>
}
