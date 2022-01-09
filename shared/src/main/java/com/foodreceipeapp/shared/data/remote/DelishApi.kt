package com.foodreceipeapp.shared.data.remote

import com.foodreceipeapp.model.Recipes
import com.foodreceipeapp.model.CuisineItem
import com.foodreceipeapp.model.IngredientItem
import com.foodreceipeapp.model.SearchItem
import com.foodreceipeapp.model.MealsPlan
import com.foodreceipeapp.model.VenuesResult
import com.foodreceipeapp.model.Recipe
import com.foodreceipeapp.shared.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path

const val foodDefaultCategoryId = "4d4b7105d754a06374d81259"

interface DelishApi {

    @GET("recipes/random")
    suspend fun getRandomRecipes(
        @Query("apiKey") apiKey: String = BuildConfig.SPOONACULAR_KEY,
        @Query("tags") tags: String?,
        @Query("number") number: Int?
    ): Recipes

    @GET(BuildConfig.CUISINES_DATA_URL)
    suspend fun getAvailableCuisines(): List<CuisineItem>

    @GET(BuildConfig.INGREDIENTS_DATA_URL)
    suspend fun getIngredients(): List<IngredientItem>

    @GET("recipes/{id}/information")
    suspend fun getRecipeInformation(
        @Path("id") id: Int?,
        @Query("apiKey") apiKey: String = BuildConfig.SPOONACULAR_KEY,
        @Query("includeNutrition") includeNutrition: Boolean? = true
    ): Recipe

    @GET("/recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("apiKey") apiKey: String = BuildConfig.SPOONACULAR_KEY,
        @Query("query") query: String?,
        @Query("cuisine") cuisine: String?,
        @Query("addRecipeInformation") addRecipeInformation: Boolean?,
        @Query("number") number: Int?,
        @Query("offset") offset: Int
    ): SearchItem

    @GET("/mealplanner/generate")
    suspend fun getMealsPlan(
        @Query("apiKey") apiKey: String = BuildConfig.SPOONACULAR_KEY,
        @Query("timeFrame") timeFrame: String = "week"
    ): MealsPlan

    @GET(BuildConfig.FOURSQUARE_Search_Url)
    suspend fun search(
        @Query("ll") latLng: String,
        @Query("v") version: String,
        @Query("client_id") clientId: String = BuildConfig.FOURSQUARE_CLIENT_ID,
        @Query("client_secret") clientSecret: String = BuildConfig.FOURSQUARE_SECRET_ID,
        @Query("radius") radius: Int,
        @Query("limit") limit: Int,
        @Query("categoryId") categoryId: String = foodDefaultCategoryId
    ): VenuesResult
}
