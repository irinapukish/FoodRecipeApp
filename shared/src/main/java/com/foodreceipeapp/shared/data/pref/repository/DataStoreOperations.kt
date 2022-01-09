package com.foodreceipeapp.shared.data.pref.repository

import androidx.datastore.preferences.core.Preferences
import com.foodreceipeapp.shared.result.Result
import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {
    suspend fun save(key: Preferences.Key<Boolean>, value: Boolean)
    fun read(key: Preferences.Key<Boolean>): Flow<Result<Boolean>>
}
