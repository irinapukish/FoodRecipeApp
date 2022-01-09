package com.foodreceipeapp.shared.usecase.searchrestaurants

import android.accounts.NetworkErrorException
import app.cash.turbine.test
import com.foodreceipeapp.shared.data.restaurants.repository.ISearchRestaurantsRepository
import com.foodreceipeapp.shared.domain.restaurants.SearchRestaurantsUseCase
import com.foodreceipeapp.shared.result.Result
import com.foodreceipeapp.shared.result.data
import com.foodreceipeapp.test_shared.MainCoroutineRule
import com.foodreceipeapp.test_shared.VENUES_ITEMS
import com.foodreceipeapp.test_shared.runBlockingTest
import com.github.javafaker.Faker
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.flow.flowOf
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchRestaurantsUseCaseTest {
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var searchRepository: ISearchRestaurantsRepository
    private lateinit var searchRestaurantsUseCase: SearchRestaurantsUseCase

    private val faker by lazy {
        Faker()
    }

    @Test
    fun `search for restaurant returns data as Result_Success value`() =
        coroutineRule.runBlockingTest {
            whenever(searchRepository.search(any(), any(), any(), any())).thenReturn(
                flowOf(Result.Success(VENUES_ITEMS))
            )

            searchRestaurantsUseCase = SearchRestaurantsUseCase(
                searchRepository, coroutineRule.testDispatcher
            )

            searchRestaurantsUseCase(createDummyParams()).test {
                Assert.assertEquals(expectItem().data, VENUES_ITEMS)
                expectComplete()
            }
        }

    @Test
    fun `search failed for restaurant returns data as Result_Error value`() =
        coroutineRule.runBlockingTest {
            whenever(searchRepository.search(any(), any(), any(), any())).thenReturn(
                flowOf(Result.Error(NetworkErrorException("Network Failure")))
            )

            searchRestaurantsUseCase = SearchRestaurantsUseCase(
                searchRepository, coroutineRule.testDispatcher
            )

            searchRestaurantsUseCase(createDummyParams()).test {
                assertThat((expectItem() as Result.Error).exception is NetworkErrorException)
                expectComplete()
            }
        }

    private fun createDummyParams() = SearchRestaurantsUseCase.Params.create(
        "${faker.address().latitude()},${faker.address().longitude()}",
        faker.number().digits(3).toString(),
        faker.number().digits(2).toInt(),
        faker.number().digits(2).toInt()
    )
}
