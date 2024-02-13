package com.domain.repository

import com.domain.model.MessageData
import kotlinx.coroutines.flow.Flow

interface IMessagesRepository {
    suspend fun addMessageToChat(chatId: String, message: MessageData)
    fun listenNewMessages(chatId: String): Flow<List<MessageData>>
}