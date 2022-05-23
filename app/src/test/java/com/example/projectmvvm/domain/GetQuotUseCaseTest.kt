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

class GetQuotUseCaseTest {

    //@RelaxedMockK si al hacer una llamada, rompe por x o y razon, ésta nos la crea,
    //devolviendo asi el resultado esperado
    @RelaxedMockK
    //@MockK
    //si se requiere acceder a un repository o una clase y ésta no está preparada, fallará el test
    private lateinit var repository: QuoteRepository

    lateinit var getQuotUseCase: GetQuotUseCase

    //se inicializa lo que se requiera para el testing
    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getQuotUseCase = GetQuotUseCase(repository)
    }

    @Test
    //fun whenTheApiDoesntReturnAnythingThenGetValuesFromDatabase()
    fun `when the api doesnt return anything then get values from database`() = runBlocking {
        //given
        //every -> siempre que se llame
        //every se usa cuando no se trabaja con corutines
        //verifica que se llame a la funcion
        coEvery { repository.getAllQuoteFromDatabase() } returns emptyList()

        //when
        getQuotUseCase()

        //then
        coVerify(exactly = 1) {
            repository.getAllQuoteFromDatabase()
        }
    }

    @Test
    fun `when the api return anything then get values from api`() = runBlocking {
        //given dar a
        val myList = mutableListOf(QuotBind("test", "Any"))
        //verifica que se llame a la funcion
        coEvery { repository.getAllQuoteFromApi() } returns myList

        //when
        val response = getQuotUseCase()


        //then
        coVerify(exactly = 1) { repository.clearQuotes() }
        coVerify(exactly = 1) { repository.insertAllDatabase(any()) }
        coVerify(exactly = 0) { repository.getAllQuoteFromDatabase() }
        assert(response == myList)
    }




    }