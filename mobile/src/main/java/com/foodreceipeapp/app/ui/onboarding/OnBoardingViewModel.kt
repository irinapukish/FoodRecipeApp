package com.foodreceipeapp.app.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodreceipeapp.app.ui.util.OnBoardingProvider
import com.foodreceipeapp.shared.domain.pref.OnBoardingCompleteActionSuspendUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    val onBoardingCompleteActionUseCase: OnBoardingCompleteActionSuspendUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(false)
    val viewState: StateFlow<Boolean>
        get() = _state

    fun getStartedClick() {
        viewModelScope.launch {
            onBoardingCompleteActionUseCase(true)
            _state.value = true
        }
    }

    fun getOnBoardingItemsList() = OnBoardingProvider.onBoardingDataList
}
