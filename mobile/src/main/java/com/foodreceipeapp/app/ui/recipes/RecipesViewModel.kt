package com.foodreceipeapp.app.ui.recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodreceipeapp.model.CuisineItem
import com.foodreceipeapp.model.IngredientItem
import com.foodreceipeapp.model.RecipesItem
import com.foodreceipeapp.shared.domain.recipes.bookmark.DeleteRecipeSuspendUseCase
import com.foodreceipeapp.shared.domain.recipes.bookmark.IsRecipeSavedSuspendUseCase
import com.foodreceipeapp.shared.domain.recipes.bookmark.SaveRecipeSuspendUseCase
import com.foodreceipeapp.shared.domain.recipes.cuisines.GetAvailableCuisinesUseCase
import com.foodreceipeapp.shared.domain.recipes.ingredients.GetIngredientsUseCase
import com.foodreceipeapp.shared.domain.recipes.random.GetRandomRecipesUseCase
import com.foodreceipeapp.shared.result.Result
import com.foodreceipeapp.shared.result.data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val randomRecipesCount = 20

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getRandomRecipesUseCase: GetRandomRecipesUseCase,
    private val getAvailableCuisinesUseCase: GetAvailableCuisinesUseCase,
    private val saveRecipeUseCase: SaveRecipeSuspendUseCase,
    private val deleteRecipeUseCase: DeleteRecipeSuspendUseCase,
    private val isRecipeSavedUseCase: IsRecipeSavedSuspendUseCase,
    private val getIngredientsUseCase: GetIngredientsUseCase
) : ViewModel() {

    val hasError = MutableStateFlow(false)
    val loading = MutableStateFlow(false)

    private val _state = MutableStateFlow(RecipesViewState())
    val viewState: StateFlow<RecipesViewState>
        get() = _state

    init {
        getHomeContent()
    }

    fun getHomeContent() {
        viewModelScope.launch {
            combine(
                getRandomRecipesUseCase(
                    GetRandomRecipesUseCase.Params.create(
                        null,
                        randomRecipesCount
                    )
                ),
                getIngredientsUseCase(Unit),
                getAvailableCuisinesUseCase(Unit)
            ) { randomRecipes, ingredients, cuisines ->
                hasError.value = cuisines is Result.Error || ingredients is Result.Error
                RecipesViewState(
                    ingredientList = ingredients.data ?: emptyList(),
                    cuisinesList = cuisines.data ?: emptyList(),
                    randomRecipes = randomRecipes.data ?: emptyList()
                )
            }.onStart {
                loading.value = true
            }.catch {
                hasError.value = true
            }.onCompletion {
                loading.value = false
            }.collect {
                _state.value = it
            }
        }
    }

    fun onBookMark(recipesItem: RecipesItem) {
        viewModelScope.launch {
            if (isRecipeSavedUseCase(recipesItem.id).data == true) {
                deleteRecipeUseCase(recipesItem.id)
            } else {
                saveRecipeUseCase(recipesItem)
            }
        }
    }

    data class RecipesViewState(
        val ingredientList: List<IngredientItem> = emptyList(),
        val cuisinesList: List<CuisineItem> = emptyList(),
        val randomRecipes: List<RecipesItem> = emptyList()
    )
}
