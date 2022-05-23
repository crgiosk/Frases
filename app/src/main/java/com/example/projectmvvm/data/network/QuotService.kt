package com.example.projectmvvm.data.network

import com.example.projectmvvm.data.model.QuoteApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuotService @Inject constructor(private val client: QuotApiClient) {
    suspend fun getQuotes(): List<QuoteApiResponse> {
        return withContext(Dispatchers.IO){
            val response = client.getAllQuots()
            response.body() ?: emptyList()
        }
    }
}