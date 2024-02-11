package com.example.chatsample.data.model

data class NewsResponse(
    val status: String,
    val news: List<NewsItem>
)