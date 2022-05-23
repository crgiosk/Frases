package com.example.projectmvvm.domain

import com.example.projectmvvm.core.Mappers.toEntity
import com.example.projectmvvm.data.QuoteRepository
import javax.inject.Inject

class GetQuotUseCase @Inject constructor(private val repository: QuoteRepository ) {
    suspend operator fun invoke(): List<QuotBind> {
        return with(repository.getAllQuoteFromApi()){
            if (this.isNotEmpty()){
                repository.clearQuotes()
                repository.insertAllDatabase(this.map { it.toEntity() })
                this
            } else {
                repository.getAllQuoteFromDatabase()
            }
        }
    }
}