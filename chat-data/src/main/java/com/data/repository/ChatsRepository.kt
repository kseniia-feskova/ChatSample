package com.data.repository

import com.domain.model.ChatData
import com.domain.model.MessageData
import com.domain.repository.IChatsRepository
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

class ChatsRepository : IChatsRepository {
    private val collection = FirebaseFirestore.getInstance().collection("chats")
    override fun getChatDocument(chatId: String): DocumentReference {
        return collection.document(chatId)
    }

    override suspend fun createChat(chat: ChatData) {
        collection.document(chat.id).set(chat).await()
    }

    override suspend fun getAllChatsForUser(userId: String): List<ChatData> {
        val documents = collection.whereArrayContains("companions", userId).get().await().documents
        return if (documents.isNotEmpty()) documents.mapNotNull {
            it.toObject(ChatData::class.java)
        } else emptyList()
    }

    override suspend fun addNewMessage(chatId: String, message: MessageData) {
        getChatDocument(chatId).collection("messages").document(message.id).set(message).await()
        getChatDocument(chatId).update("timestamp", message.timestamp).await()
    }

    override suspend fun getLastMessage(chatId: String): MessageData? {
        val documents = getChatDocument(chatId).collection("messages")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .limit(1)
            .get().await()
        return if (documents.isEmpty) null else
            documents.mapNotNull { it.toObject(MessageData::class.java) }[0]
    }

    override suspend fun getCompanionForChat(chatId: String, currentId: String): String {
        val chat = getChatDocument(chatId).get().await().toObject(ChatData::class.java)
        return chat?.companions?.firstOrNull { it != currentId } ?: ""
    }

    override suspend fun getAllCompanionsForChat(chatId: String): List<String> {
        val chat = getChatDocument(chatId).get().await().toObject(ChatData::class.java)
        return chat?.companions ?: emptyList()
    }

    override suspend fun deleteChat(chatId: String) {
        val messages = collection.document(chatId).collection("messages").get().await()
        messages.forEach {
            collection.document(chatId).collection("messages").document(it.id).delete()
        }
        collection.document(chatId).delete().await()
    }

    override suspend fun deleteMessage(messageId: String, chatId: String) {
        val lastMessage = getLastMessage(chatId)
        collection.document(chatId).collection("messages").document(messageId).delete()
        if (lastMessage?.id == messageId) {
            val newLastMessage = getLastMessage(chatId)
            getChatDocument(chatId).update("timestamp", newLastMessage?.timestamp).await()
        }
    }
}