package com.foodreceipeapp.app.ui.recipedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodreceipeapp.model.RecipesItem
import com.foodreceipeapp.shared.domain.recipes.bookmark.SaveRecipeSuspendUseCase
import com.foodreceipeapp.shared.domain.recipes.information.GetRecipeInformationSuspendUseCase
import com.foodreceipeapp.shared.result.Result
import com.foodreceipeapp.shared.result.data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val getRecipeInformationUseCase: GetRecipeInformationSuspendUseCase,
    private val saveRecipeUseCase: SaveRecipeSuspendUseCase
) : ViewModel() {

    val isloading = MutableStateFlow(false)
    private val _state = MutableStateFlow(RecipesDetailsViewState())
    val viewState: StateFlow<RecipesDetailsViewState>
        get() = _state

    fun getRecipeDetails(id: Int) {
        isloading.value = true
        viewModelScope.launch {
            val recipeDetail = getRecipeInformationUseCase(id)
            when (recipeDetail) {
                is Result.Error -> {
                    _state.value = RecipesDetailsViewState(hasError = true)
                }
                else -> _state.value = if (recipeDetail.data != null) {
                    RecipesDetailsViewState(recipe = recipeDetail.data!!)
                } else {
                    RecipesDetailsViewState(isEmpty = true)
                }
            }
            isloading.value = false
        }
    }

    fun saveRecipe(recipesItem: RecipesItem) {
        viewModelScope.launch {
            saveRecipeUseCase(recipesItem)
        }
    }

    data class RecipesDetailsViewState(
        val recipe: RecipesItem = RecipesItem(),
        val isEmpty: Boolean = false,
        val hasError: Boolean = false
    )
}
