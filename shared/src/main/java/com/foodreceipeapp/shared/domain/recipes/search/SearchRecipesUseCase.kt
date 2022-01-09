package com.foodreceipeapp.shared.domain.recipes.search

import androidx.paging.PagingData
import com.foodreceipeapp.model.RecipesItem
import com.foodreceipeapp.shared.data.recipes.search.repository.SearchRepository
import com.foodreceipeapp.shared.domain.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRecipesUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) : UseCase<SearchRecipesUseCase.Params, Flow<PagingData<RecipesItem>>>() {

    override fun execute(parameters: Params): Flow<PagingData<RecipesItem>> =
        searchRepository.searchRecipes(parameters.query, parameters.cuisine)

    class Params private constructor(
        val query: String?,
        val cuisine: String?
    ) {

        companion object {
            @JvmStatic
            fun create(
                query: String? = "",
                cuisine: String? = ""
            ): Params {
                return Params(query, cuisine)
            }
        }
    }
}
