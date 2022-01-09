package com.foodreceipeapp.shared.database.utils

import com.foodreceipeapp.model.RecipesItem
import com.foodreceipeapp.shared.data.db.recipes.entities.RecipeEntity
import com.foodreceipeapp.shared.data.db.recipes.mapper.RecipeMapper

internal class FakeRecipeMapper : RecipeMapper {

    override fun mapToDataBaseRecipe(recipesItem: RecipesItem): RecipeEntity =
        RecipeEntity(
            recipesItem.id ?: 0,
            recipesItem.title ?: "",
            recipesItem.sustainable ?: false,
            recipesItem.glutenFree ?: false,
            recipesItem.veryPopular ?: false,
            recipesItem.vegetarian ?: false,
            recipesItem.dairyFree ?: false,
            recipesItem.veryHealthy ?: false,
            recipesItem.vegan ?: false,
            recipesItem.cheap ?: false,
            recipesItem.spoonacularScore ?: 0.0,
            recipesItem.aggregateLikes ?: 0,
            recipesItem.sourceName ?: "",
            recipesItem.creditsText ?: "",
            recipesItem.readyInMinutes ?: 0,
            recipesItem.image ?: "",
            recipesItem.percentCarbs ?: 0.0,
            recipesItem.percentProtein ?: 0.0,
            recipesItem.percentFat ?: 0.0,
            recipesItem.nutrientsAmount ?: 0.0,
            recipesItem.nutrientsName ?: "",
            "", "",
            recipesItem.servings ?: 0
        )

    override fun mapToDataRecipe(recipeEntity: RecipeEntity): RecipesItem =
        RecipesItem(
            recipeEntity.recipeId,
            recipeEntity.sustainable,
            recipeEntity.glutenFree,
            recipeEntity.veryPopular,
            recipeEntity.nutrientsAmount,
            recipeEntity.title,
            recipeEntity.aggregateLikes,
            recipeEntity.creditsText,
            recipeEntity.readyInMinutes,
            recipeEntity.dairyFree,
            recipeEntity.vegetarian,
            recipeEntity.image,
            recipeEntity.veryHealthy,
            recipeEntity.vegan,
            recipeEntity.cheap,
            recipeEntity.spoonScore,
            recipeEntity.sourceName,
            recipeEntity.percentCarbs,
            recipeEntity.percentProtein,
            recipeEntity.percentFat,
            recipeEntity.nutrientsAmount,
            recipeEntity.nutrientsName,
            recipeEntity.servings,
            emptyList(),
            emptyList()
        )
}
