package com.domain.model

data class NewsResponse(
    val status: String,
    val news: List<NewsItem>
)