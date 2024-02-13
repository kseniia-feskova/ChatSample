package com.example.chatsample.data.repository

import android.util.Log
import com.domain.model.MessageData
import com.domain.repository.IChatsRepository
import com.domain.repository.IMessagesRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class MessagesRepository @Inject constructor(
    private val chatsRepository: IChatsRepository
) : IMessagesRepository {

    override suspend fun addMessageToChat(chatId: String, message: MessageData) {
        chatsRepository.addNewMessage(chatId, message)
    }

    override fun listenNewMessages(chatId: String): Flow<List<MessageData>> = callbackFlow<List<MessageData>> {
        val listener = chatsRepository.getChatDocument(chatId).collection("messages")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                val messages = snapshot?.documents?.
                mapNotNull { doc ->
                    doc.toObject(MessageData::class.java)
                } ?: emptyList()
                this.trySend(messages).isSuccess
            }

        awaitClose { listener.remove() }
    }.catch { error ->
        Log.e("listenNewMessages", "error = ${error.message}")
    }
}