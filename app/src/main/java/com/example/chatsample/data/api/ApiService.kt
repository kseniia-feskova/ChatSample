package com.example.chatsample.data.api

import com.example.chatsample.data.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("latest-news")
    fun getLatestNews(
        @Query("language") language: String = "en",
        @Query("apiKey") apiKey: String = API_KEY
    ): Call<NewsResponse>

    companion object {
        const val API_KEY = "iIf_xP8w5ZCzqnmeyDZHXb8mzrFA5tCU2ackodWVeUKCKrZs"
    }
}