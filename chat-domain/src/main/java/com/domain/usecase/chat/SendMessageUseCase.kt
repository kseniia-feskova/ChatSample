package com.domain.usecase.chat

import com.domain.model.MessageData
import com.domain.repository.IMessagesRepository
import com.domain.repository.IUserRepository
import com.domain.utils.encrypt
import com.google.firebase.Timestamp
import java.util.*
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val usersRepository: IUserRepository,
    private val messagesRepository: IMessagesRepository,
) : ISendMessageUseCase {
    override suspend fun invoke(text: String, chatId: String) {
        val newMessage = createMessage(text)
        messagesRepository.addMessageToChat(chatId, newMessage)
    }

    private fun createMessage(text: String): MessageData {
        return MessageData(
            id = UUID.randomUUID().toString(),
            authorId = usersRepository.getLoggedId(),
            text = encrypt(text),
            timestamp = Timestamp(System.currentTimeMillis()/1000L, 0)
        )
    }
}