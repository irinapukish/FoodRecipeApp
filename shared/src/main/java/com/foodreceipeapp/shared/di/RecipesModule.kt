package com.foodreceipeapp.shared.di

import com.foodreceipeapp.shared.data.db.datastore.RecipesLocalDataStore
import com.foodreceipeapp.shared.data.plan.remote.GetMealPlanRemoteDataSource
import com.foodreceipeapp.shared.data.plan.remote.MealPlanDataSource
import com.foodreceipeapp.shared.data.plan.repository.GetMealPlanRepository
import com.foodreceipeapp.shared.data.plan.repository.MealPlanRepository
import com.foodreceipeapp.shared.data.recipes.cuisines.remote.GetCuisinesDataSource
import com.foodreceipeapp.shared.data.recipes.cuisines.remote.GetCuisinesRemoteDataSource
import com.foodreceipeapp.shared.data.recipes.cuisines.repository.CuisinesRepository
import com.foodreceipeapp.shared.data.recipes.cuisines.repository.GetCuisinesRepository
import com.foodreceipeapp.shared.data.recipes.info.remote.RecipeInformationDataSource
import com.foodreceipeapp.shared.data.recipes.info.remote.RecipeInformationRemoteDataSource
import com.foodreceipeapp.shared.data.recipes.info.repository.GetRecipeInformationRepository
import com.foodreceipeapp.shared.data.recipes.info.repository.RecipeInformationRepository
import com.foodreceipeapp.shared.data.recipes.ingredients.remote.GetIngredientsDataSource
import com.foodreceipeapp.shared.data.recipes.ingredients.remote.GetIngredientsRemoteDataSource
import com.foodreceipeapp.shared.data.recipes.ingredients.repository.GetIngredientsRepository
import com.foodreceipeapp.shared.data.recipes.ingredients.repository.IngredientsRepository
import com.foodreceipeapp.shared.data.recipes.random.remote.GetRandomRecipesRemoteDataSource
import com.foodreceipeapp.shared.data.recipes.random.remote.RandomRecipesRemoteDataSource
import com.foodreceipeapp.shared.data.recipes.random.repository.GetRandomRecipesRepository
import com.foodreceipeapp.shared.data.recipes.random.repository.RandomRecipesRepository
import com.foodreceipeapp.shared.data.recipes.search.remote.SearchDataSource
import com.foodreceipeapp.shared.data.recipes.search.remote.SearchRecipesDataSource
import com.foodreceipeapp.shared.data.recipes.search.repository.SearchRecipesRepository
import com.foodreceipeapp.shared.data.recipes.search.repository.SearchRepository
import com.foodreceipeapp.shared.data.remote.DelishApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RecipesModule {

    @Provides
    fun provideRandomRecipesDataSource(api: DelishApi): RandomRecipesRemoteDataSource =
        GetRandomRecipesRemoteDataSource(api)

    @Provides
    fun provideRandomRecipesRepository(
        randomRecipesRemoteDataSource: RandomRecipesRemoteDataSource
    ): RandomRecipesRepository =
        GetRandomRecipesRepository(randomRecipesRemoteDataSource)

    @Provides
    fun provideGetCuisinesDataSource(api: DelishApi): GetCuisinesDataSource =
        GetCuisinesRemoteDataSource(api)

    @Provides
    fun provideCuisinesRepository(
        getCuisinesDataSource: GetCuisinesDataSource
    ): CuisinesRepository =
        GetCuisinesRepository(getCuisinesDataSource)

    @Provides
    fun provideRecipeInformationDataSource(api: DelishApi): RecipeInformationDataSource =
        RecipeInformationRemoteDataSource(api)

    @Provides
    fun provideRecipeInformationRepository(
        recipeInformationDataSource: RecipeInformationDataSource,
        recipesLocalDataStore: RecipesLocalDataStore
    ): RecipeInformationRepository =
        GetRecipeInformationRepository(
            recipeInformationDataSource,
            recipesLocalDataStore
        )

    @Provides
    fun provideSearchDataSource(api: DelishApi): SearchDataSource =
        SearchRecipesDataSource(api)

    @Provides
    fun provideSearchRepository(
        rearchDataSource: SearchDataSource
    ): SearchRepository =
        SearchRecipesRepository(rearchDataSource)

    @Provides
    fun provideMealPlanDataSource(api: DelishApi): MealPlanDataSource =
        GetMealPlanRemoteDataSource(api)

    @Provides
    fun provideMealPlanRepository(
        mealPlanDataSource: MealPlanDataSource
    ): MealPlanRepository =
        GetMealPlanRepository(mealPlanDataSource)

    @Provides
    fun provideIngredientsDataSource(api: DelishApi): GetIngredientsDataSource =
        GetIngredientsRemoteDataSource(api)

    @Provides
    fun provideIngredientsRepository(
        getIngredientsDataSource: GetIngredientsDataSource
    ): IngredientsRepository =
        GetIngredientsRepository(getIngredientsDataSource)
}
