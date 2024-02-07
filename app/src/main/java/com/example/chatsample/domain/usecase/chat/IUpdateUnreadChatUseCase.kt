package com.example.chatsample.domain.usecase.chat

interface IUpdateUnreadChatUseCase {
    suspend operator fun invoke(userId: String? = null, chatId: String, isRead: Boolean)

}