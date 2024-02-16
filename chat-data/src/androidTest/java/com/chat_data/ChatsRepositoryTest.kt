package com.chat_data

import com.data.api.FirestoreService
import com.data.repository.ChatsRepository
import com.google.firebase.Timestamp
import org.junit.Before
import org.junit.Test
import java.util.*

class ChatsRepositoryTest {
    private val collection = firestoreService.getCollection("chats")

    private lateinit var chatsRepository: ChatsRepository
    private lateinit var firestoreService: FirestoreService

    @Before
    fun setUp() {
        firestoreService = FirestoreService()
        chatsRepository = ChatsRepository(firestoreService)
    }

    @Test
    fun getChatDocumentTest() {
        chatsRepository.getChatDocument(TEST_CHAT_ID)
    }

    companion object {
        const val TEST_CHAT_ID = "8f643e2c-26e4-47fe-822a-0c59800cfbfc"
        const val TEST_COLLECTION = "chats"
        const val TEST_COLLECTION_MESSAGES = "messages"
        val TEST_DOCUMENT = mutableMapOf(
            "id" to TEST_CHAT_ID,
            "companions" to listOf("de8d7424-6064-4507-9921-d94af292b481", "74744428-7d62-4f41-b3fc-1f8f6bf9ed56"),
            "timestamp" to Timestamp(Date(1707282774000)),
        )
    }
}