package com.foodreceipeapp.shared.usecase

import com.foodreceipeapp.test_shared.MainCoroutineRule
import com.foodreceipeapp.shared.domain.SuspendUseCase
import com.foodreceipeapp.shared.result.Result
import com.foodreceipeapp.test_shared.runBlockingTest
import kotlinx.coroutines.CoroutineDispatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SuspendUseCaseTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private lateinit var testDispatcher: CoroutineDispatcher

    private lateinit var useCase: TestSuspendUseCase

    @Before
    fun setup() {
        testDispatcher = coroutineRule.testDispatcher
        useCase = TestSuspendUseCase(testDispatcher)
    }

    @Test
    fun `running use case should return result class`() {
        var result: Result<Result<Unit>>? = null

        coroutineRule.runBlockingTest {
            result = useCase.invoke(Unit)
        }

        assert(result is Result.Success)
    }

    private inner class TestSuspendUseCase(
        dispatcher: CoroutineDispatcher
    ) : SuspendUseCase<Unit, Result<Unit>>(dispatcher) {
        override suspend fun execute(parameters: Unit): Result<Unit> {
            return Result.Success(Unit)
        }
    }
}
