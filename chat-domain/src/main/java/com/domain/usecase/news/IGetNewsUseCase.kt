package com.domain.usecase.news

import com.domain.model.NewsUI

interface IGetNewsUseCase {
    operator fun invoke(onSuccess: (List<NewsUI>) -> Unit, onError: (String) -> Unit)
}