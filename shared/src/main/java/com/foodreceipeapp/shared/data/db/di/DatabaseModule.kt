package com.foodreceipeapp.shared.data.db.di

import android.content.Context
import androidx.room.Room
import com.foodreceipeapp.shared.data.db.Constants
import com.foodreceipeapp.shared.data.db.DelishDataBase
import com.foodreceipeapp.shared.data.db.JsonConverter
import com.foodreceipeapp.shared.data.db.commons.MIGRATIONS
import com.foodreceipeapp.shared.data.db.datastore.RecipesLocalDataStore
import com.foodreceipeapp.shared.data.db.recipes.mapper.RecipeMapper
import com.foodreceipeapp.shared.data.db.recipes.mapper.RecipeMapperImpl
import com.foodreceipeapp.shared.data.db.recipes.recipedatastore.RecipesDatabaseDataStore
import com.foodreceipeapp.shared.data.db.recipes.tables.RecipesTable
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideDelishDatabase(@ApplicationContext context: Context): DelishDataBase {
        return Room.databaseBuilder(
            context,
            DelishDataBase::class.java,
            Constants.DATABASE_NAME
        ).addMigrations(*MIGRATIONS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRecipesTable(delishDataBase: DelishDataBase): RecipesTable {
        return delishDataBase.recipesTable
    }

    @Provides
    @Singleton
    fun provideJsonConverter(moshi: Moshi): JsonConverter = JsonConverter(moshi)

    @Provides
    @Singleton
    fun provideRecipesMapper(jsonConverter: JsonConverter): RecipeMapper {
        return RecipeMapperImpl(jsonConverter)
    }

    @Provides
    @Singleton
    fun provideRecipeDataStore(
        recipesTable: RecipesTable,
        recipeMapper: RecipeMapper,
    ): RecipesLocalDataStore {
        return RecipesDatabaseDataStore(recipesTable, recipeMapper)
    }
}
