package com.foodreceipeapp.app.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.foodreceipeapp.app.ui.recipedetails.RecipeDetailsViewModel
import com.foodreceipeapp.shared.domain.recipes.bookmark.SaveRecipeSuspendUseCase
import com.foodreceipeapp.shared.domain.recipes.information.GetRecipeInformationSuspendUseCase
import com.foodreceipeapp.shared.result.Result
import com.foodreceipeapp.test_shared.MainCoroutineRule
import com.foodreceipeapp.test_shared.RECIPE_ID
import com.foodreceipeapp.test_shared.RECIPE_ITEM
import com.foodreceipeapp.test_shared.runBlockingTest
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.assertj.core.api.Assertions.assertThat
import java.lang.Exception

class RecipeDetailsViewModelTest {
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var getRecipeInformationUseCase: GetRecipeInformationSuspendUseCase

    @MockK
    private lateinit var saveRecipeUseCase: SaveRecipeSuspendUseCase
    private lateinit var recipeDetailsViewModel: RecipeDetailsViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        saveRecipeUseCase = mockk()

        recipeDetailsViewModel = RecipeDetailsViewModel(
            getRecipeInformationUseCase,
            saveRecipeUseCase
        )
    }

    @Test
    fun `get recipe information emits ui states successfully`() {
        mainCoroutineRule.runBlockingTest {
            coEvery { getRecipeInformationUseCase(any()) } returns Result.Success(RECIPE_ITEM)
            recipeDetailsViewModel.getRecipeDetails(RECIPE_ID)
            recipeDetailsViewModel.viewState.test {
                val expectItem = expectItem()
                assertThat(expectItem.recipe.id).isEqualTo(RECIPE_ITEM.id)
                assertThat(expectItem.hasError).isFalse()
            }
        }
    }

    @Test
    fun `get recipe information with error emits error state`() {
        mainCoroutineRule.runBlockingTest {
            coEvery { getRecipeInformationUseCase(any()) } returns Result.Error(Exception(""))
            recipeDetailsViewModel.getRecipeDetails(RECIPE_ID)
            recipeDetailsViewModel.viewState.test {
                assertThat(expectItem().hasError).isTrue()
            }
        }
    }

    @Test
    fun `save recipe should call save recipe use case`() {
        mainCoroutineRule.runBlockingTest {
            recipeDetailsViewModel.saveRecipe(mockk())
            coVerify { saveRecipeUseCase(any()) }
        }
    }
}
