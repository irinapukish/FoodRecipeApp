package com.foodreceipeapp.app.bookmark

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.foodreceipeapp.app.ui.bookmark.BookmarkViewModel
import com.foodreceipeapp.shared.domain.recipes.bookmark.DeleteRecipeSuspendUseCase
import com.foodreceipeapp.shared.domain.recipes.bookmark.GetSavedRecipesUseCase
import com.foodreceipeapp.shared.result.Result
import com.foodreceipeapp.test_shared.MainCoroutineRule
import com.foodreceipeapp.test_shared.RECIPES_ITEMS
import com.foodreceipeapp.test_shared.runBlockingTest
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import io.mockk.coEvery
import io.mockk.mockk
import java.lang.Exception

class BookmarkViewModelTest {
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var getSavedRecipesUseCase: GetSavedRecipesUseCase

    @MockK
    private lateinit var deleteRecipeUseCase: DeleteRecipeSuspendUseCase

    private lateinit var bookmarkViewModel: BookmarkViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getSavedRecipesUseCase = mockk()
        deleteRecipeUseCase = mockk()
    }

    @Test
    fun `get saved recipes from database should emits ui state`() =
        mainCoroutineRule.runBlockingTest {
            coEvery { getSavedRecipesUseCase(Unit) } returns flowOf(Result.Success(RECIPES_ITEMS))

            bookmarkViewModel = BookmarkViewModel(
                getSavedRecipesUseCase,
                deleteRecipeUseCase
            )

            bookmarkViewModel.state.test {
                val expectedItem = expectItem()
                assertThat(expectedItem.recipes).isEqualTo(RECIPES_ITEMS)
                assertThat(expectedItem.isEmpty).isFalse()
            }
        }

    @Test
    fun `empty recipes from database should emits ui empty state`() =
        mainCoroutineRule.runBlockingTest {
            coEvery { getSavedRecipesUseCase(Unit) } returns flowOf(Result.Success(emptyList()))

            bookmarkViewModel = BookmarkViewModel(
                getSavedRecipesUseCase,
                deleteRecipeUseCase
            )

            bookmarkViewModel.state.test {
                val expectedItem = expectItem()
                assertThat(expectedItem.recipes).isEmpty()
                assertThat(expectedItem.isEmpty).isTrue()
            }
        }

    @Test
    fun `failed data from database should emits ui empty state`() =
        mainCoroutineRule.runBlockingTest {
            coEvery { getSavedRecipesUseCase(Unit) } returns flowOf(Result.Error(Exception("")))

            bookmarkViewModel = BookmarkViewModel(
                getSavedRecipesUseCase,
                deleteRecipeUseCase
            )

            bookmarkViewModel.state.test {
                val expectedItem = expectItem()
                assertThat(expectedItem.recipes).isEmpty()
                assertThat(expectedItem.isEmpty).isTrue()
            }
        }
}
