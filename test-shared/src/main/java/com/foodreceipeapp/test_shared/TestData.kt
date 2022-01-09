package com.foodreceipeapp.test_shared

import com.foodreceipeapp.model.CuisineItem
import com.foodreceipeapp.model.IngredientItem
import com.foodreceipeapp.model.RecipesItem
import com.github.javafaker.Faker

val RECIPE_ID = Faker().number().digits(3).toInt()
val recipe = "{id:$RECIPE_ID,vegetarian:true}"
val recipes = "{recipes:[$recipe]}"

val faker = Faker()

val RECIPE_ITEM = RecipesItem(
    id = faker.number().digits(3).toInt(),
    title = faker.lorem().sentence(),
    sustainable = false,
    glutenFree = false,
    veryPopular = false,
    vegetarian = false,
    dairyFree = false,
    veryHealthy = false,
    vegan = false,
    cheap = false,
    spoonacularScore = faker.number().digit().toDouble(),
    aggregateLikes = faker.number().digit().toInt(),
    sourceName = faker.lorem().word(),
    creditsText = faker.lorem().sentence(),
    readyInMinutes = faker.number().digit().toInt(),
    image = faker.internet().image(),
    percentCarbs = faker.number().digit().toDouble(),
    percentProtein = faker.number().digit().toDouble(),
    percentFat = faker.number().digit().toDouble(),
    nutrientsAmount = faker.number().digit().toDouble(),
    nutrientsName = faker.lorem().word()
)

val RECIPES_ITEMS = listOf(
    RECIPE_ITEM.copy(id = faker.number().digits(3).toInt()),
    RECIPE_ITEM.copy(id = faker.number().digits(3).toInt()),
    RECIPE_ITEM.copy(id = faker.number().digits(3).toInt()),
    RECIPE_ITEM.copy(id = faker.number().digits(3).toInt())
)

val CUISINE_ITEM = CuisineItem(
    image = faker.internet().image(),
    title = faker.lorem().sentence(),
    color = faker.color().hex()
)

val CUISINES_ITEMS = listOf(CUISINE_ITEM, CUISINE_ITEM, CUISINE_ITEM)

val INGREDIENT_ITEM = IngredientItem(
    id = faker.number().digits(3).toInt(),
    title = faker.lorem().sentence(),
    image = faker.internet().image(),
    background = faker.color().hex()
)

val INGREDIENTS = listOf(
    INGREDIENT_ITEM.copy(id = faker.number().digits(3).toInt()),
    INGREDIENT_ITEM.copy(id = faker.number().digits(3).toInt())
)
