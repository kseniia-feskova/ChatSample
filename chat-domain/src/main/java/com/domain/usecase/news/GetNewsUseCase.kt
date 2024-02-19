package com.domain.usecase.news

import android.util.Log
import com.domain.model.NewsItem
import com.domain.model.NewsUI
import com.domain.repository.INewsRepository
import io.reactivex.rxjava3.schedulers.Schedulers
import java.net.UnknownHostException
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val newsRepository: INewsRepository) :
    IGetNewsUseCase {
    override fun invoke(onSuccess: (List<NewsUI>) -> Unit, onError: (String) -> Unit) {
        val result = newsRepository.getCurrentNewsFromApi().subscribeOn(Schedulers.io())
            .observeOn(Schedulers.single())
            .map { response ->
                newsRepository.saveNewsLocally(response.news).subscribe().dispose()
                response.news.map { mapperForNews(it) }
            }
            .subscribe({ news ->
                Log.e("GetNewsUseCase", "Subscribe successful, news =${news.size} ")
                onSuccess(news)
            }, { error ->
                if (error is UnknownHostException) {
                    getNewsFromDB(onSuccess, onError)
                } else {
                    onError("Cannot get news for you. Error = ${error.message}")
                }
            })
    }

    private fun getNewsFromDB(onSuccess: (List<NewsUI>) -> Unit, onError: (String) -> Unit) {
        val localResult = newsRepository.getCurrentNewsFromDB().map { listOfNews ->
            listOfNews.map { mapperForNews(it) }
        }.subscribe(
            { news ->
                onSuccess(news)
            },
            { localError ->
                onError(localError.message ?: "Cannot take data from local db")
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