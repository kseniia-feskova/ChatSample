package com.domain.usecase.chat

interface ISendMessageUseCase {
    suspend operator fun invoke(text: String, chatId: String)

}