package com.domain.usecase.chat

import com.domain.repository.IUserRepository

class UpdateUnreadChatUseCase(
    private val usersRepository: IUserRepository
) : IUpdateUnreadChatUseCase {
    override suspend fun invoke(userId: String?, chatId: String, isRead: Boolean) {
        usersRepository.updateUnreadChat(userId, chatId, isRead)
    }

}