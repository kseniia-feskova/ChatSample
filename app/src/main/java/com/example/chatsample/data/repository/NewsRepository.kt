package com.example.chatsample.data.repository

import com.example.chatsample.data.api.ApiService
import com.example.chatsample.data.model.NewsResponse
import com.example.chatsample.domain.repository.INewsRepository
import retrofit2.Call
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsApiService: ApiService) : INewsRepository {
    override fun getCurrentNews() {
        newsApiService.getLatestNews().enqueue(object : retrofit2.Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>,
                response: retrofit2.Response<NewsResponse>
            ) {
                if (response.isSuccessful) {
                    val newsResponse = response.body()
                    println(newsResponse?.news?.size)
                } else {
                    println("Ошибка при получении данных: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                println("Ошибка при выполнении запроса: ${t.message}")
            }
        })
    }
}