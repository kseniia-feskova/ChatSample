package com.domain.repository

import com.domain.model.NewsItem
import com.domain.model.NewsResponse
import io.reactivex.rxjava3.core.Single

interface INewsRepository {
    fun getCurrentNewsFromApi(): Single<NewsResponse>
    fun getCurrentNewsFromDB(): List<NewsItem>
    fun saveNewsLocally(news: List<NewsItem>)
}