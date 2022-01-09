package com.foodreceipeapp.shared.data.location.mapper

import android.location.Location
import com.foodreceipeapp.model.LocationModel

fun Location.toDataModel(): LocationModel {
    return LocationModel(
        latitude,
        longitude,
    )
}
