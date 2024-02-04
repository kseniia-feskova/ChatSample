package com.example.chatsample.data.repository

import android.util.Log
import com.example.chatsample.domain.model.MessageData
import com.example.chatsample.domain.repository.IMessagesRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch

class MessagesRepository : IMessagesRepository {
    private val collection = FirebaseFirestore.getInstance().collection("chats")
    override fun listenNewMessages(chatId: String): Flow<List<MessageData>> = callbackFlow {
        val listener = collection.document(chatId).collection("messages")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                val messages =
                    snapshot?.documents?.mapNotNull { doc -> doc.toObject(MessageData::class.java) }
                        ?: emptyList()
                this.trySend(messages).isSuccess
            }

        awaitClose { listener.remove() }
    }.catch { error ->
        Log.e("listenNewMessages", "error = ${error.message}")
    }
}