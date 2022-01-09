package com.foodreceipeapp.app.launcher

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.foodreceipeapp.app.ui.launcher.LauncherViewModel
import com.foodreceipeapp.shared.data.pref.PreferencesKeys
import com.foodreceipeapp.shared.data.pref.repository.DataStoreOperations
import com.foodreceipeapp.shared.domain.pref.OnBoardingCompletedUseCase
import com.foodreceipeapp.shared.result.Result
import com.foodreceipeapp.test_shared.MainCoroutineRule
import com.foodreceipeapp.test_shared.runBlockingTest
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import com.foodreceipeapp.app.ui.launcher.LaunchDestination.MAIN_ACTIVITY
import com.foodreceipeapp.app.ui.launcher.LaunchDestination.ON_BOARDING
import com.foodreceipeapp.app.ui.launcher.LauncherViewState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.assertj.core.api.Assertions.assertThat

class LauncherViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @MockK
    lateinit var repository: DataStoreOperations

    private lateinit var launcherViewModel: LauncherViewModel
    private lateinit var onBoardingCompletedUseCase: OnBoardingCompletedUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        onBoardingCompletedUseCase = OnBoardingCompletedUseCase(
            repository, coroutineRule.testDispatcher
        )
    }

    @Test
    fun `not completed onBoarding should navigate to onBoarding`() = coroutineRule.runBlockingTest {
        coEvery { repository.read(PreferencesKeys.onBoardingCompletedKey) } returns flowOf(
            Result.Success(
                false
            )
        )
        launcherViewModel = LauncherViewModel(onBoardingCompletedUseCase)
        launcherViewModel.state.test {
            assertThat(expectItem()).isEqualTo(LauncherViewState(ON_BOARDING))
        }
    }

    @Test
    fun `completed onBoarding should navigate to main`() = coroutineRule.runBlockingTest {
        coEvery { repository.read(PreferencesKeys.onBoardingCompletedKey) } returns flowOf(
            Result.Success(
                true
            )
        )
        launcherViewModel = LauncherViewModel(onBoardingCompletedUseCase)

        launcherViewModel.state.test {
            assertThat(expectItem()).isEqualTo(LauncherViewState(MAIN_ACTIVITY))
        }
    }
}
