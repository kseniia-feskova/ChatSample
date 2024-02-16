package com.chat_data

import com.domain.model.NewsItem

object DataProvider {
    fun getNews(count: Int): List<NewsItem> {
        if (count < 0) return emptyList()
        return if (count > newsList.size) {
            newsList
        } else {
            newsList.subList(0, count)
        }
    }

    private val newsList = listOf(
        NewsItem(
            "0",
            "News_1",
            "Description",
            "Url_to_news_1",
            "Jack",
            null,
            "en",
            listOf("Cats", "Machines"),
            "Tomorrow"
        ),
        NewsItem(
            "1",
            "News_2",
            "Description2",
            "Url_to_news_2",
            "John",
            null,
            "en",
            listOf("Cats", "Machines"),
            "Tomorrow"
        ),
        NewsItem(
            "2",
            "News_3",
            "Description3",
            "Url_to_news_3",
            "Jack",
            null,
            "en",
            listOf("Cats", "Machines"),
            "Tomorrow"
        ),
        NewsItem(
            "3",
            "News_4",
            "Description",
            "Url_to_news_5",
            "Jack",
            null,
            "en",
            listOf("Cats", "Machines"),
            "Tomorrow"
        ),
        NewsItem(
            "4",
            "News_5",
            "Description",
            "Url_to_news_6",
            "Jack",
            null,
            "en",
            listOf("Cats", "Machines"),
            "Tomorrow"
        ),
        NewsItem(
            "5",
            "News_6",
            "Description",
            "Url_to_news_6",
            "Jack",
            null,
            "en",
            listOf("Cats", "Machines"),
            "Tomorrow"
        ),
        NewsItem(
            "6",
            "News_7",
            "Description",
            "Url_to_news_7",
            "Jack",
            null,
            "en",
            listOf("Cats", "Machines"),
            "Tomorrow"
        )
    )
}