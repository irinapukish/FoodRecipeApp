package com.foodreceipeapp.shared.domain.pref

import com.foodreceipeapp.shared.data.pref.PreferencesKeys
import com.foodreceipeapp.shared.data.pref.repository.DataStoreOperations
import com.foodreceipeapp.shared.di.IoDispatcher
import com.foodreceipeapp.shared.domain.FlowUseCase
import com.foodreceipeapp.shared.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OnBoardingCompletedUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreOperations,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, Boolean>(dispatcher) {
    override fun execute(parameters: Unit): Flow<Result<Boolean>> =
        dataStoreRepository.read(PreferencesKeys.onBoardingCompletedKey)
}
