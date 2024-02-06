package com.example.chatsample.domain.usecase.chat

interface ICreateChatUseCase {
    suspend operator fun invoke(companionId: String): String

}