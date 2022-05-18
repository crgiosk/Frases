package com.example.projectmvvm.data.network

import com.example.projectmvvm.data.model.Quote
import retrofit2.Response
import retrofit2.http.GET

interface QuotApiClient {

    @GET("/.json")
    suspend fun getAllQuots(): Response<List<Quote>>
}