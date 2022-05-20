package com.example.projectmvvm.domain

import com.example.projectmvvm.data.model.Quote
import com.example.projectmvvm.data.model.QuoteProvider
import javax.inject.Inject

class GetRandomQuotUseCase @Inject constructor(
    private val quoteProvider: QuoteProvider
) {
    operator fun invoke(): Quote? {
        with(quoteProvider.quoteList) {
            return if (this.isNotEmpty()) {
                this[this.indices.random()]
            } else {
                null
            }
        }
    }
}