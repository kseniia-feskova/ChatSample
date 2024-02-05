package com.example.chatsample.data.repository

import com.example.chatsample.domain.model.ChatData
import com.example.chatsample.domain.model.MessageData
import com.example.chatsample.domain.repository.IChatsRepository
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

class ChatsRepository : IChatsRepository {
    private val collection = FirebaseFirestore.getInstance().collection("chats")
    override fun getChatDocument(chatId: String): DocumentReference {
        return collection.document(chatId)
    }

    override suspend fun getAllChatsForUser(userId: String): List<ChatData> {
        val documents =
            collection.whereArrayContains("companions", userId).get().await().documents
        return if (documents.isNotEmpty()) documents.mapNotNull {
            it.toObject(ChatData::class.java)
        } else emptyList()
    }

    override suspend fun getLastMessage(chatId: String): MessageData? {
        val documents = getChatDocument(chatId).collection("messages")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .limit(1)
            .get().await()
        return if (documents.isEmpty) null else
            documents.mapNotNull { it.toObject(MessageData::class.java) }[0]
    }

}