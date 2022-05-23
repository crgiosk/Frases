package com.example.projectmvvm.di

import android.content.Context
import androidx.room.Room
import com.example.projectmvvm.data.db.QuotDataBase
import com.example.projectmvvm.data.db.dao.QuotDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val QUOT_DATABASE_NAME = "quot_database"

    @Singleton
    @Provides
    fun getDatabaseInstance(@ApplicationContext context: Context): QuotDataBase {
        return Room.databaseBuilder(context, QuotDataBase::class.java, QUOT_DATABASE_NAME).build()
    }

    @Singleton
    @Provides
    fun getDaoInstance(dataBase: QuotDataBase): QuotDAO = dataBase.getQuotDao()

}