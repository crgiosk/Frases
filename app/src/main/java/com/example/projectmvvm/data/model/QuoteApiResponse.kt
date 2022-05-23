package com.example.projectmvvm.data.model

import androidx.annotation.Keep
import com.example.projectmvvm.core.QuotInterface
import com.google.gson.annotations.SerializedName
@Keep
data class QuoteApiResponse(
    @SerializedName("quote")
    override val quote: String = String(),
    @SerializedName("author")
    override val author: String = String()
): QuotInterface