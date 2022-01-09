package com.foodreceipeapp.app.onboarding

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.foodreceipeapp.app.ui.onboarding.OnBoardingViewModel
import com.foodreceipeapp.shared.data.pref.repository.DataStoreOperations
import com.foodreceipeapp.shared.domain.pref.OnBoardingCompleteActionSuspendUseCase
import com.foodreceipeapp.test_shared.MainCoroutineRule
import com.foodreceipeapp.test_shared.runBlockingTest
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class OnBoardingViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @MockK
    lateinit var repository: DataStoreOperations

    private lateinit var onBoardingCompleteActionUseCase: OnBoardingCompleteActionSuspendUseCase
    private lateinit var onBoardingViewModel: OnBoardingViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        onBoardingCompleteActionUseCase = OnBoardingCompleteActionSuspendUseCase(
            repository, coroutineRule.testDispatcher
        )
        onBoardingViewModel = OnBoardingViewModel(onBoardingCompleteActionUseCase)
    }

    @Test
    fun `get started clicked should update the pref`() = coroutineRule.runBlockingTest {
        onBoardingViewModel.getStartedClick()
        onBoardingViewModel.viewState.test {
            assertThat(expectItem()).isEqualTo(true)
        }
    }
}
