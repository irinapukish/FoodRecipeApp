package com.foodreceipeapp.shared.data.recipes.search.remote

import com.foodreceipeapp.model.SearchItem

interface SearchDataSource {
    suspend fun searchRecipes(
        query: String?,
        cuisine: String?,
        offset: Int
    ): SearchItem
}
