package com.foodreceipeapp.shared.data.recipes.search.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.foodreceipeapp.model.RecipesItem
import com.foodreceipeapp.model.toUiModel
import com.foodreceipeapp.shared.data.recipes.search.remote.SearchDataSource
import javax.inject.Inject

const val initialPageIndex = 1
class SearchSource @Inject constructor(
    private val searchDataSource: SearchDataSource,
    private val query: String?,
    private val cuisine: String?
) : PagingSource<Int, RecipesItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecipesItem> {
        return try {
            val page = params.key ?: initialPageIndex
            val searchItem = searchDataSource.searchRecipes(
                offset = page,
                query = query,
                cuisine = cuisine
            )
            LoadResult.Page(
                data = searchItem.results.map { it.toUiModel() },
                prevKey = if (page == initialPageIndex) null else page - 1,
                nextKey = if (searchItem.results.isEmpty()) null else page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RecipesItem>): Int? {
        return null
    }
}
