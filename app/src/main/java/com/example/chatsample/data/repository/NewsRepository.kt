package com.example.chatsample.data.repository

import com.example.chatsample.data.api.ApiService
import com.example.chatsample.data.db.NewsDB
import com.example.chatsample.data.model.NewsItem
import com.example.chatsample.data.model.NewsResponse
import com.example.chatsample.domain.repository.INewsRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsApiService: ApiService,
    private val newsDB: NewsDB
) : INewsRepository {
    override fun getCurrentNewsFromApi(): Single<NewsResponse> {
        return newsApiService.getLatestNews()
    }

    override fun getCurrentNewsFromDB(): List<NewsItem> {
        return newsDB.getNewsDAO().getNews()
    }

    override fun saveNewsLocally(news: List<NewsItem>) {
        newsDB.getNewsDAO().deleteAll()
        val firstTenNews = if (news.size > 10) {
            news.subList(0, 10)
        } else {
            news
        }
        newsDB.getNewsDAO().addNews(firstTenNews)
    }
}
/* Solution without rxJava
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
*/