package com.example.chatsample.data.repository

import com.data.api.ApiService
import com.example.chatsample.data.db.NewsDB
import com.domain.model.NewsItem
import com.domain.model.NewsResponse
import com.domain.repository.INewsRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsApiService: ApiService,
    private val newsDB: NewsDB
) : INewsRepository {
    override fun getCurrentNewsFromApi(): Single<NewsResponse> {
        return newsApiService.getLatestNews()
    }

    override fun getCurrentNewsFromDB(): Single<List<NewsItem>> {
        return newsDB.getNewsDAO().getNews()
    }

    override fun saveNewsLocally(news: List<NewsItem>): Completable {
        newsDB.getNewsDAO().deleteAll()
        val firstTenNews = if (news.size > 10) {
            news.subList(0, 10)
        } else {
            news
        }
        return newsDB.getNewsDAO().addNews(firstTenNews)
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