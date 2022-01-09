package com.foodreceipeapp.app.ui.recipes.graph

import androidx.annotation.VisibleForTesting
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.foodreceipeapp.app.ui.bookmark.BookmarkViewModel
import com.foodreceipeapp.app.ui.ingredient.IngredientFullList
import com.foodreceipeapp.app.ui.main.MainContent
import com.foodreceipeapp.app.ui.plan.MealPlanViewModel
import com.foodreceipeapp.app.ui.recipes.RecipesViewModel

object MainDestinations {
    const val MAIN_ROUTE = "main"
    const val INGREDIENT_ROUTE = "ingredient"
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@VisibleForTesting
@Composable
fun HomeNavGraph(
    startDestination: String = MainDestinations.MAIN_ROUTE,
    onCuisineSearch: (String) -> Unit,
    onIngredientSearch: (String) -> Unit,
    onExplore: () -> Unit,
    onDetails: (Int) -> Unit,
) {
    val recipesViewModel: RecipesViewModel = viewModel()
    val bookmarkViewModel: BookmarkViewModel = viewModel()
    val mealPlanViewModel: MealPlanViewModel = viewModel()
    val navController = rememberNavController()
    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable((MainDestinations.MAIN_ROUTE)) {
            MainContent(
                viewModel = recipesViewModel,
                bookmarkViewModel = bookmarkViewModel,
                mealPlanViewModel = mealPlanViewModel,
                onIngredientContent = actions.onIngredientContent,
                onCuisineSearch = onCuisineSearch,
                onDetails = onDetails,
                onExploreClicked = onExplore,
                onIngredientSearch = onIngredientSearch
            )
        }
        composable((MainDestinations.INGREDIENT_ROUTE)) {
            IngredientFullList(
                viewModel = recipesViewModel,
                onExploreClicked = onExplore,
                onIngredientSearch = onIngredientSearch,
            )
        }
    }
}

class MainActions(
    navController: NavHostController
) {
    val onIngredientContent: () -> Unit = {
        navController.navigate(
            route = MainDestinations.INGREDIENT_ROUTE
        )
    }
}
