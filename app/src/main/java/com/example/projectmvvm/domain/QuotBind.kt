package com.example.projectmvvm.domain

import com.example.projectmvvm.core.QuotInterface

data class QuotBind(
    override val quote: String,
    override val author: String
): QuotInterface
