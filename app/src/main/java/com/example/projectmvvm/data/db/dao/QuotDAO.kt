package com.example.projectmvvm.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.projectmvvm.data.db.entities.QuoteEntity

@Dao
interface QuotDAO {

    @Query("select * from quote_table ORDER BY author_column DESC")
    suspend fun getAllQuots(): List<QuoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotList: List<QuoteEntity>)

    @Query("DELETE FROM quote_table")
    suspend fun clearAll()
}