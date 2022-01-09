package com.foodreceipeapp.shared.database.utils

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.foodreceipeapp.shared.data.db.DelishDataBase

internal fun createMemoryDataBase(): DelishDataBase {
    return Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        DelishDataBase::class.java
    ).build()
}
