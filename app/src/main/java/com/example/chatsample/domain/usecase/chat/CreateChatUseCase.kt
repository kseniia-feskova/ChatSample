package com.example.chatsample.domain.usecase.chat

import com.example.chatsample.domain.model.ChatData
import com.example.chatsample.domain.repository.IChatsRepository
import com.example.chatsample.domain.repository.IUserRepository
import java.util.*
import javax.inject.Inject

class CreateChatUseCase @Inject constructor(
    private val usersRepository: IUserRepository,
    private val chatsRepository: IChatsRepository
) : ICreateChatUseCase {
    override suspend fun invoke(companionId: String): String {
        val chatData = getChatData(companionId)
        chatsRepository.createChat(chatData)
        return chatData.id
    }

    private fun getChatData(companionId: String): ChatData {
        val currentId = usersRepository.getLoggedId()
        return ChatData(
            id = UUID.randomUUID().toString(),
            companions = listOf(currentId, companionId)
        )
    }
}