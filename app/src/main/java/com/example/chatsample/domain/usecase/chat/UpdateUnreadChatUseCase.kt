package com.example.chatsample.domain.usecase.chat

import com.example.chatsample.domain.repository.IUserRepository

class UpdateUnreadChatUseCase(
    private val usersRepository: IUserRepository
) : IUpdateUnreadChatUseCase {
    override suspend fun invoke(userId: String?, chatId: String, isRead: Boolean) {
        usersRepository.updateUnreadChat(userId, chatId, isRead)
    }

}