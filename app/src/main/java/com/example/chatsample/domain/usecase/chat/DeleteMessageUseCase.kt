package com.example.chatsample.domain.usecase.chat

import com.example.chatsample.domain.model.MessageUI
import com.example.chatsample.domain.repository.IChatsRepository

class DeleteMessageUseCase(
    private val chatsRepository: IChatsRepository
) : IDeleteMessageUseCase {
    override suspend fun invoke(message: MessageUI, chatId:String) {
        chatsRepository.deleteMessage(message.id, chatId)
    }
}