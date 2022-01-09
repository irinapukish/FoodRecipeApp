package com.foodreceipeapp.shared.domain.restaurants

import com.foodreceipeapp.shared.utils.fourSquareVersionFormat
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class CreateFoursquareVersionUseCase @Inject constructor() {

    operator fun invoke(date: Date): String {
        val outputFormatter: DateFormat =
            SimpleDateFormat(fourSquareVersionFormat, Locale.getDefault())
        return outputFormatter.format(date)
    }
}
