package com.example.projectmvvm.data.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuoteProvider @Inject constructor() {
    var quoteList = listOf<Quote>()
}