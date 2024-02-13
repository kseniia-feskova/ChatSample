package com.domain.usecase.chat

import com.domain.model.MessageData
import com.domain.model.MessageUI
import com.domain.repository.IMessagesRepository
import com.domain.repository.IUserRepository
import com.domain.utils.decrypt
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetListOfMessagesUseCase @Inject constructor(
    private val userRepository: IUserRepository,
    private val messagesRepository: IMessagesRepository
) : IGetListOfMessagesUseCase {

    override operator fun invoke(chatId: String): Flow<List<MessageUI>> {
        return messagesRepository.listenNewMessages(chatId).map {
            it.map { message ->
                mapMessageToUI(message)
            }.sortedBy { it.timestamp }
        }
    }

    private suspend fun mapMessageToUI(messageData: MessageData): MessageUI {
        val author = userRepository.getUserOrNull(messageData.authorId)
        val currentId = userRepository.getLoggedId()
        return MessageUI(
            id = messageData.id,
            authorId = messageData.authorId,
            authorName = decrypt(author?.name ?: ""),
            text = decrypt(messageData.text),
            isMyMessage = messageData.authorId == currentId,
            timestamp = messageData.timestamp
        )
    }
}