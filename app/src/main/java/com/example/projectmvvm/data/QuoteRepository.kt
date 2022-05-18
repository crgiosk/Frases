package com.example.projectmvvm.data

import com.example.projectmvvm.data.model.Quote
import com.example.projectmvvm.data.model.QuoteProvider
import com.example.projectmvvm.data.network.QuotService

class QuoteRepository {

    private val api = QuotService()
    suspend fun getAllQuote(): List<Quote> {
        val response = api.getQuotes()
        QuoteProvider.quoteList = response
        return response
    }
}