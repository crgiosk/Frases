package com.example.projectmvvm.data

import com.example.projectmvvm.core.Mappers.toQuot
import com.example.projectmvvm.data.db.dao.QuotDAO
import com.example.projectmvvm.data.db.entities.QuoteEntity
import com.example.projectmvvm.data.network.QuotService
import com.example.projectmvvm.domain.QuotBind
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuotService,
    private val quotDAO: QuotDAO
){
    suspend fun getAllQuoteFromApi(): List<QuotBind> {
        return api.getQuotes().map { it.toQuot() }
    }

    suspend fun getAllQuoteFromDatabase(): List<QuotBind> {
        return quotDAO.getAllQuots().map { it.toQuot() }
    }

    suspend fun insertAllDatabase(list: List<QuoteEntity>) {
        quotDAO.insertAll(list)
    }

    suspend fun clearQuotes() {
        quotDAO.clearAll()
    }
}