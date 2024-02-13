package com.domain.usecase.chat

import com.domain.repository.IChatsRepository
import com.domain.repository.IUserRepository
import javax.inject.Inject

class GetCompanionForChatUseCase @Inject constructor(
    private val usersRepository: IUserRepository,
    private val chatsRepository: IChatsRepository
) : IGetCompanionForChatUseCase {
    override suspend fun invoke(chatId: String): String {
        val currentId = usersRepository.getLoggedId()
        return chatsRepository.getCompanionForChat(chatId, currentId)
    }
}