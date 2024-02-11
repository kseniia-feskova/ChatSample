package com.example.chatsample.domain.usecase.news

import com.example.chatsample.domain.model.NewsUI


interface IGetNewsUseCase {
    operator fun invoke(onSuccess: (List<NewsUI>) -> Unit, onError: (String) -> Unit)
}