package com.foodreceipeapp.app.ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodreceipeapp.model.RecipesItem
import com.foodreceipeapp.shared.domain.recipes.bookmark.DeleteRecipeSuspendUseCase
import com.foodreceipeapp.shared.domain.recipes.bookmark.GetSavedRecipesUseCase
import com.foodreceipeapp.shared.result.data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getSavedRecipesUseCase: GetSavedRecipesUseCase,
    private val deleteRecipeUseCase: DeleteRecipeSuspendUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(BookmarkViewState())
    val state: StateFlow<BookmarkViewState>
        get() = _state

    init {
        getSavedRecipes()
    }

    private fun getSavedRecipes() {
        viewModelScope.launch {
            getSavedRecipesUseCase(Unit).collect {
                if (it.data.isNullOrEmpty()) {
                    _state.value = BookmarkViewState(isEmpty = true)
                } else {
                    _state.value = BookmarkViewState(recipes = it.data!!)
                }
            }
        }
    }

    fun deleteRecipe(recipe: RecipesItem) {
        viewModelScope.launch {
            deleteRecipeUseCase(recipe.id)
        }
    }

    data class BookmarkViewState(
        val recipes: List<RecipesItem> = emptyList(),
        val isEmpty: Boolean = false
    )
}
