package com.example.chatsample.domain.usecase.news

import android.util.Log
import com.example.chatsample.data.model.NewsItem
import com.example.chatsample.domain.model.NewsUI
import com.example.chatsample.domain.repository.INewsRepository
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val newsRepository: INewsRepository) :
    IGetNewsUseCase {
    override fun invoke(onSuccess: (List<NewsUI>) -> Unit, onError: (String) -> Unit) {
        val result = newsRepository.getCurrentNewsFromApi().subscribeOn(Schedulers.io())
            .observeOn(Schedulers.single())
            .map { response ->
                newsRepository.saveNewsLocally(response.news)
                response.news.map { mapperForNews(it) }
            }
            .subscribe({ news ->
                Log.e("GetNewsUseCase", "Subscribe successfull, news =${news.size} ")
                onSuccess(news)
            }, { error ->
                println("Ошибка при выполнении запроса: ${error.message}")
                onSuccess(newsRepository.getCurrentNewsFromDB().map { mapperForNews(it) })
            })
    }

    private fun mapperForNews(new: NewsItem): NewsUI {
        return NewsUI(
            id = new.id,
            title = new.title,
            description = new.description,
            url = new.url,
            author = new.author,
            image = new.image
        )
    }
}