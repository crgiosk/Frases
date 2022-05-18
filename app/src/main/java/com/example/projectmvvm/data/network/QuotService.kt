package com.example.projectmvvm.data.network

import com.example.projectmvvm.core.RetrofitHelper
import com.example.projectmvvm.data.model.Quote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuotService {

    private val retrofitHelper = RetrofitHelper.getRetrofitInstance()

    suspend fun getQuotes(): List<Quote> {
        return withContext(Dispatchers.IO){
            val response = retrofitHelper.create(QuotApiClient::class.java).getAllQuots()
            response.body() ?: emptyList()
        }
    }
}