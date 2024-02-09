package com.example.chatsample.domain.usecase.chat

import android.util.Log
import com.example.chatsample.domain.utils.decrypt
import com.example.chatsample.domain.model.ChatData
import com.example.chatsample.domain.model.ChatUI
import com.example.chatsample.domain.repository.IChatsRepository
import com.example.chatsample.domain.repository.IUserRepository
import com.google.firebase.Timestamp
import javax.inject.Inject

class GetAllChatsUseCase @Inject constructor(
    private val userRepository: IUserRepository,
    private val chatsRepository: IChatsRepository,
) : IGetAllChatsUseCase {
    override suspend fun invoke(): List<ChatUI> {
        val currentId = userRepository.getLoggedId()
        val listOfChatData = chatsRepository.getAllChatsForUser(currentId)
        return listOfChatData.map {
            mapChatToUI(it, currentId)
        }
    }

    private suspend fun mapChatToUI(chatData: ChatData, currentId: String): ChatUI {
        val lastMessage = chatsRepository.getLastMessage(chatData.id)
        val companionId = chatData.companions.first { it != currentId }
        val companionName = decrypt(userRepository.getUserOrNull(companionId)?.name ?: "")
        val currentChats =
            userRepository.getUserOrNull(userRepository.getLoggedId())?.chats?.get(chatData.id) == true
        return ChatUI(
            id = chatData.id,
            author = companionName,
            lastMessage = decrypt(lastMessage?.text ?: ""),
            isRead = currentChats,
            timestamp = lastMessage?.timestamp ?: Timestamp(0, 0)
        )
    }
}