package com.domain.usecase.chat

import com.domain.model.MessageUI
import com.domain.repository.IChatsRepository

class DeleteMessageUseCase(
    private val chatsRepository: IChatsRepository
) : IDeleteMessageUseCase {
    override suspend fun invoke(message: MessageUI, chatId:String) {
        chatsRepository.deleteMessage(message.id, chatId)
    }
}