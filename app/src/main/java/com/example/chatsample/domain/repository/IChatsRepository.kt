package com.example.chatsample.domain.repository

import com.example.chatsample.domain.model.ChatData
import com.example.chatsample.domain.model.MessageData
import com.google.firebase.firestore.DocumentReference

interface IChatsRepository {
    fun getChatDocument(chatId: String): DocumentReference
    suspend fun getAllChatsForUser(userId: String): List<ChatData>
    suspend fun addNewMessage(chatId: String, message: MessageData)
    suspend fun getLastMessage(chatId: String): MessageData?
    suspend fun createChat(chat: ChatData)
}