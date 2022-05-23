package com.example.projectmvvm.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.projectmvvm.data.db.dao.QuotDAO
import com.example.projectmvvm.data.db.entities.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class QuotDataBase: RoomDatabase() {
    abstract fun getQuotDao(): QuotDAO
}