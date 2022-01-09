package com.foodreceipeapp.app.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import app.cash.turbine.test
import com.foodreceipeapp.app.ui.search.SearchRecipesViewModel
import com.foodreceipeapp.app.ui.search.SearchType
import com.foodreceipeapp.shared.domain.recipes.search.SearchRecipesUseCase
import com.foodreceipeapp.test_shared.MainCoroutineRule
import com.foodreceipeapp.test_shared.RECIPE_ITEM
import com.foodreceipeapp.test_shared.runBlockingTest
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchRecipesViewModelTest {
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var searchRecipesUseCase: SearchRecipesUseCase

    private lateinit var searchRecipesViewModel: SearchRecipesViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        coEvery { searchRecipesUseCase(any()) } returns
            flowOf(PagingData.from(listOf(RECIPE_ITEM)))
        searchRecipesViewModel = SearchRecipesViewModel(searchRecipesUseCase)
    }

    @Test
    fun `search for quote should emits ui state success`() = mainCoroutineRule.runBlockingTest {
        searchRecipesViewModel.searchRecipes("test key", SearchType.QUERY).test {
            assertThat(expectItem()).isNotNull
            expectComplete()
        }
    }
}
