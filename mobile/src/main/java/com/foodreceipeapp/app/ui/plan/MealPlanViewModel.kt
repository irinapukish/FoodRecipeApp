package com.foodreceipeapp.app.ui.plan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodreceipeapp.model.DayMeal
import com.foodreceipeapp.shared.domain.plan.GetMealPlanUseCase
import com.foodreceipeapp.shared.result.Result
import com.foodreceipeapp.shared.result.data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealPlanViewModel @Inject constructor(
    private val getMealPlanUseCase: GetMealPlanUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MealPlanViewState())
    val viewState: StateFlow<MealPlanViewState>
        get() = _state

    init {
        getMealPlan()
    }

    private fun getMealPlan() {
        _state.value = MealPlanViewState(loading = true)
        viewModelScope.launch {
            val recipeDetail = getMealPlanUseCase(Unit)
            when (recipeDetail) {
                is Result.Error -> {
                    _state.value = MealPlanViewState(hasError = true)
                }
                else -> _state.value = if (!recipeDetail.data.isNullOrEmpty()) {
                    MealPlanViewState(meals = recipeDetail.data!!, isEmpty = false)
                } else {
                    MealPlanViewState(isEmpty = true)
                }
            }
            MealPlanViewState(loading = true)
        }
    }

    data class MealPlanViewState(
        val loading: Boolean = false,
        val meals: List<DayMeal?> = emptyList(),
        val isEmpty: Boolean = true,
        val hasError: Boolean = false
    )
}
