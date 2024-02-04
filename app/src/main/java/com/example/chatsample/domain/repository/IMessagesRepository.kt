package com.example.chatsample.domain.repository

import com.example.chatsample.domain.model.MessageData
import kotlinx.coroutines.flow.Flow

interface IMessagesRepository {
    fun listenNewMessages(chatId: String): Flow<List<MessageData>>
}