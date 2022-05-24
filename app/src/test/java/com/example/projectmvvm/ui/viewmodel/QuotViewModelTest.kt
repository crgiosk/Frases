package com.example.projectmvvm.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.projectmvvm.core.UIState
import com.example.projectmvvm.data.QuoteRepository
import com.example.projectmvvm.domain.GetQuotUseCase
import com.example.projectmvvm.domain.GetRandomQuotUseCase
import com.example.projectmvvm.domain.QuotBind
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class QuotViewModelTest {

    @RelaxedMockK
    private lateinit var repository: QuoteRepository
    private lateinit var quotViewModel: QuotViewModel


    @RelaxedMockK
    private lateinit var getQuotUseCase: GetQuotUseCase

    @RelaxedMockK
    private lateinit var getRandomQuotUseCase: GetRandomQuotUseCase

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onStart() {
        MockKAnnotations.init(this)
        quotViewModel = QuotViewModel(getQuotUseCase, getRandomQuotUseCase)

        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created at the first time, get all quotes and set the first value`() =
        runTest {
            //Given
            val quote = listOf(QuotBind("Holi", "Aris"), QuotBind("Dame un like", "Otro Aris "))
            coEvery { getQuotUseCase() } returns quote
            //When
            quotViewModel.onCreate()
            //Then
            assert(quotViewModel.getAllQuotsLiveData().value == UIState.OnSuccess(quote.first()))
        }

}