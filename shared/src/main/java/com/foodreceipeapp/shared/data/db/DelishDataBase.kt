package com.foodreceipeapp.shared.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.foodreceipeapp.shared.data.db.recipes.entities.RecipeEntity
import com.foodreceipeapp.shared.data.db.recipes.tables.RecipesTable

@Database(
    entities = [
        RecipeEntity::class
    ],
    version = Constants.VERSION
)
internal abstract class DelishDataBase : RoomDatabase() {
    abstract val recipesTable: RecipesTable
}
