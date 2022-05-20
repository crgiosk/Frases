package com.example.projectmvvm.data

import com.example.projectmvvm.data.model.Quote
import com.example.projectmvvm.data.model.QuoteProvider
import com.example.projectmvvm.data.network.QuotService
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuotService,
    private val quoteProvider: QuoteProvider
){
    suspend fun getAllQuote(): List<Quote> {
        val response = api.getQuotes()
        quoteProvider.quoteList = response
        return response
    }
}