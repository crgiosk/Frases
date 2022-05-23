package com.example.projectmvvm.data.db.entities

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.projectmvvm.core.QuotInterface

@Entity(tableName = "quote_table")
@Keep
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "quote_column")
    override val quote: String,
    @ColumnInfo(name = "author_column")
    override val author: String
): QuotInterface