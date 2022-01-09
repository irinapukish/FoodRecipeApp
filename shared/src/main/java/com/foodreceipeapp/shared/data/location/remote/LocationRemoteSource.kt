package com.foodreceipeapp.shared.data.location.remote

import android.Manifest
import android.content.Context
import androidx.core.content.ContextCompat
import com.foodreceipeapp.model.LocationModel
import com.foodreceipeapp.shared.data.location.mapper.toDataModel
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.SettingsClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.tasks.CancellationToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * Get current location using [FusedLocationProviderClient].
 */
class LocationRemoteSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context.applicationContext),
    private val cancellationToken: CancellationToken,
    private val locationSettingsClient: SettingsClient,
    private val locationSettingsRequest: LocationSettingsRequest
) : ILocationRemoteDataSource {

    override fun getCurrentLocation(): Flow<LocationModel> {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        return flow {
            requireLocationEnabled()
            fusedLocationClient.getCurrentLocation(
                LocationRequest.PRIORITY_HIGH_ACCURACY,
                cancellationToken
            ).await().let {
                emit(it.toDataModel())
            }
        }
    }

    private suspend fun requireLocationEnabled() {
        runCatching {
            locationSettingsClient.checkLocationSettings(locationSettingsRequest).await()
        }.getOrElse {
            throw when (it) {
                is ResolvableApiException -> Exception("Please turn on location service")
                else -> it
            }
        }
    }
}
