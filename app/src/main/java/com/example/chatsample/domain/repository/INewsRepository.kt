package com.example.chatsample.domain.repository

import com.example.chatsample.data.model.NewsItem
import com.example.chatsample.data.model.NewsResponse
import io.reactivex.rxjava3.core.Single

interface INewsRepository {
    fun getCurrentNewsFromApi(): Single<NewsResponse>
    fun getCurrentNewsFromDB(): List<NewsItem>
    fun saveNewsLocally(news: List<NewsItem>)
}