package com.example.chatsample.domain.repository

import com.example.chatsample.data.model.NewsResponse
import io.reactivex.rxjava3.core.Single

interface INewsRepository {
    fun getCurrentNews(): Single<NewsResponse>
}