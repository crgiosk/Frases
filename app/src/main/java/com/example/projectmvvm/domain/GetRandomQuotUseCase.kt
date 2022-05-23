package com.example.projectmvvm.domain

import com.example.projectmvvm.data.QuoteRepository
import javax.inject.Inject

class GetRandomQuotUseCase @Inject constructor(
    private val repository: QuoteRepository
) {
    suspend operator fun invoke(): QuotBind? {
        with(repository.getAllQuoteFromDatabase()) {
            return if (this.isNotEmpty()) {
                this[this.indices.random()]
            } else {
                null
            }
        }
    }
}