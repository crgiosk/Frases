package com.example.projectmvvm.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
@Keep
data class Quote(
    @SerializedName("quote")
    val quote: String,
    @SerializedName("author")
    val author: String
)