package com.domain.repository

import com.domain.model.ChatData
import com.domain.model.MessageData
import com.google.firebase.firestore.DocumentReference

interface IChatsRepository {
    fun getChatDocument(chatId: String): DocumentReference
    suspend fun getAllChatsForUser(userId: String): List<ChatData>
    suspend fun addNewMessage(chatId: String, message: MessageData)
    suspend fun getLastMessage(chatId: String): MessageData?
    suspend fun createChat(chat: ChatData)
    suspend fun getCompanionForChat(chatId: String, currentId: String): String
    suspend fun getAllCompanionsForChat(chatId: String): List<String>
    suspend fun deleteChat(chatId: String)
    suspend fun deleteMessage(messageId:String, chatId: String)
}