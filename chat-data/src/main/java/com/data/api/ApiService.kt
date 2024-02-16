package com.data.api

import com.domain.model.NewsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("latest-news")
    fun getLatestNews(
        @Query("language") language: String = "fr",
        @Query("apiKey") apiKey: String = API_KEY
    ): Single<NewsResponse>

    companion object {
        const val API_KEY = "iIf_xP8w5ZCzqnmeyDZHXb8mzrFA5tCU2ackodWVeUKCKrZs"
        const val BASE_URL = "https://api.currentsapi.services/v1/"
    }
}