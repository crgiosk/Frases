package com.example.projectmvvm.di

import com.example.projectmvvm.data.network.QuotApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://drawsomething-59328-default-rtdb.europe-west1.firebasedatabase.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiClient(retrofit: Retrofit): QuotApiClient = retrofit.create(QuotApiClient::class.java)

}