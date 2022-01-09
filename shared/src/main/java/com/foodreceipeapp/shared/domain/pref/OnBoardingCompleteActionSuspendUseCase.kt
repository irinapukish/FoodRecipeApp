package com.foodreceipeapp.shared.domain.pref

import com.foodreceipeapp.shared.data.pref.PreferencesKeys
import com.foodreceipeapp.shared.data.pref.repository.DataStoreOperations
import com.foodreceipeapp.shared.di.IoDispatcher
import com.foodreceipeapp.shared.domain.SuspendUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class OnBoardingCompleteActionSuspendUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreOperations,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : SuspendUseCase<Boolean, Unit>(dispatcher) {
    override suspend fun execute(parameters: Boolean) =
        dataStoreRepository.save(PreferencesKeys.onBoardingCompletedKey, true)
}
