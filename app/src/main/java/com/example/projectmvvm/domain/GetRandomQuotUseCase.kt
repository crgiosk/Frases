package com.example.projectmvvm.domain

import com.example.projectmvvm.data.model.Quote
import com.example.projectmvvm.data.model.QuoteProvider

class GetRandomQuotUseCase {
    operator fun invoke(): Quote? {
        with(QuoteProvider.quoteList) {
            return if (this.isNotEmpty()) {
                this[this.indices.random()]
            } else {
                null
            }
        }
    }
}