package com.example.chatsample.data.api

import com.example.chatsample.data.model.NewsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("latest-news")
    fun getLatestNews(
        @Query("language") language: String = "en",
        @Query("apiKey") apiKey: String = API_KEY
    ): Single<NewsResponse>

    @GET("search")
    fun getNewsByKeyword(
        @Query("language") language: String = "en",
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("keywords") keywords: String
    ): Single<NewsResponse>

    companion object {
        const val API_KEY = "iIf_xP8w5ZCzqnmeyDZHXb8mzrFA5tCU2ackodWVeUKCKrZs"
    }
}