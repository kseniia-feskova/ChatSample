package com.example.chatsample.domain.usecase.chat

import com.example.chatsample.domain.repository.IChatsRepository
import com.example.chatsample.domain.repository.IUserRepository


class DeleteChatUseCase(
    private val chatsRepository: IChatsRepository,
    private val userRepository: IUserRepository
) : IDeleteChatUseCase {
    override suspend fun invoke(chatId: String) {
        val companions = chatsRepository.getAllCompanionsForChat(chatId)
        chatsRepository.deleteChat(chatId)
        companions.forEach { userId ->
            userRepository.deleteChat(userId, chatId)
        }
    }
}