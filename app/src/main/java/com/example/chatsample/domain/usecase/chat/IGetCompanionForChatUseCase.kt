package com.example.chatsample.domain.usecase.chat

interface IGetCompanionForChatUseCase {
    suspend operator fun invoke(chatId: String): String

}