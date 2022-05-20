package com.example.projectmvvm.domain

import com.example.projectmvvm.data.QuoteRepository
import com.example.projectmvvm.data.model.Quote
import javax.inject.Inject

class GetQuotUseCase @Inject constructor(private val repository: QuoteRepository ) {
    suspend operator fun invoke(): List<Quote> = repository.getAllQuote()
}