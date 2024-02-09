package com.example.chatsample.domain.usecase.news

import com.example.chatsample.domain.repository.INewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val newsRepository: INewsRepository) :
    IGetNewsUseCase {
    override fun invoke() {
        newsRepository.getCurrentNews()
    }
}