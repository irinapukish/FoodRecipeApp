package com.foodreceipeapp.app.ui.search

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.foodreceipeapp.model.RecipesItem
import com.foodreceipeapp.shared.domain.recipes.search.SearchRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchRecipesViewModel @Inject constructor(
    private val searchRecipesUseCase: SearchRecipesUseCase
) : ViewModel() {

    fun searchRecipes(
        searchKey: String,
        searchType: SearchType
    ): Flow<PagingData<RecipesItem>> {

        val query = if (searchType == SearchType.QUERY) searchKey else ""
        val cuisine = if (searchType == SearchType.CUISINE) searchKey else ""

        val params = SearchRecipesUseCase.Params.create(
            query,
            cuisine
        )
        return searchRecipesUseCase(params)
    }
}

enum class SearchType {
    CUISINE,
    QUERY
}
