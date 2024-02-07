package com.example.chatsample.domain.usecase.chat

import com.example.chatsample.domain.model.MessageUI
import kotlinx.coroutines.flow.Flow

interface IGetListOfMessagesUseCase {
    operator fun invoke(chatId: String): Flow<List<MessageUI>>

}