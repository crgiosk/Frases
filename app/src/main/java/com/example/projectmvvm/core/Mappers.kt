package com.example.projectmvvm.core

import com.example.projectmvvm.data.db.entities.QuoteEntity
import com.example.projectmvvm.domain.QuotBind

object Mappers {
    fun QuotInterface.toQuot() = QuotBind(author = this.author, quote = this.quote)
    fun QuotInterface.toEntity() = QuoteEntity(author = this.author, quote = this.quote)
}