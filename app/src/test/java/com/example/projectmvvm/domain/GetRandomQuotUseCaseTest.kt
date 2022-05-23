package com.example.projectmvvm.domain

import com.example.projectmvvm.data.QuoteRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetRandomQuotUseCaseTest {


    //@RelaxedMockK si al hacer una llamada, rompe por x o y razon, ésta nos la crea,
    //devolviendo asi el resultado esperado
    @RelaxedMockK
    //@MockK
    //si se requiere acceder a un repository o una clase y ésta no está preparada, fallará el test
    private lateinit var repository: QuoteRepository

    private lateinit var getRandomQuotUseCase: GetRandomQuotUseCase


    //se inicializa lo que se requiera para el testing
    @Before
    fun onInit(){
        MockKAnnotations.init(this)
        getRandomQuotUseCase = GetRandomQuotUseCase(repository)
    }

    @Test
    fun `when the db is empty return null`() = runBlocking {
        //given
        coEvery { repository.getAllQuoteFromDatabase() } returns emptyList()

        //when se llame a...
        //y para que se llame a .... hay que hacer primero lo de arriba, osea given...
        val response = getRandomQuotUseCase()

        //then
        assert(response == null)
    }

    @Test
    fun `when the db is not empty return QuotBind`() = runBlocking {
        //give
        val myList = mutableListOf(QuotBind("test", "Any"))
        coEvery { repository.getAllQuoteFromDatabase() } returns myList

        //when
        val response = getRandomQuotUseCase()

        //then
        assert(response == myList.first())
    }
}