package com.foodreceipeapp.app.ui.launcher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodreceipeapp.shared.domain.pref.OnBoardingCompletedUseCase
import com.foodreceipeapp.shared.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LauncherViewModel @Inject constructor(
    private val onBoardingCompletedUseCase: OnBoardingCompletedUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LauncherViewState())
    val state: StateFlow<LauncherViewState>
        get() = _state

    init {
        getLaunchDestination()
    }

    private fun getLaunchDestination() {
        viewModelScope.launch {
            onBoardingCompletedUseCase(Unit).collect { result ->
                if (result is Result.Success) {
                    if (result.data) {
                        _state.value = LauncherViewState(LaunchDestination.MAIN_ACTIVITY)
                    } else {
                        LauncherViewState(LaunchDestination.ON_BOARDING)
                    }
                } else {
                    LauncherViewState(LaunchDestination.ON_BOARDING)
                }
            }
        }
    }
}

enum class LaunchDestination {
    ON_BOARDING,
    MAIN_ACTIVITY
}

data class LauncherViewState(
    val launchDestination: LaunchDestination = LaunchDestination.ON_BOARDING,
)
