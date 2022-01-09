package com.foodreceipeapp.shared.usecase

import com.foodreceipeapp.test_shared.MainCoroutineRule
import com.foodreceipeapp.shared.domain.FlowUseCase
import com.foodreceipeapp.shared.result.Result
import com.foodreceipeapp.test_shared.runBlockingTest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FlowSuspendUseCaseTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private lateinit var testDispatcher: CoroutineDispatcher
    private lateinit var useCase: ExceptionUseCase

    @Before
    fun setup() {
        testDispatcher = coroutineRule.testDispatcher
        useCase = ExceptionUseCase(testDispatcher)
    }

    @Test
    fun `exception emits Result#Error`() {
        coroutineRule.runBlockingTest {
            val result = useCase(Unit)
            assert(result.first() is Result.Error)
        }
    }

    inner class ExceptionUseCase(dispatcher: CoroutineDispatcher) :
        FlowUseCase<Unit, Unit>(dispatcher) {
        override fun execute(parameters: Unit): Flow<Result<Unit>> = flow {
            throw Exception("Test exception")
        }
    }
}
