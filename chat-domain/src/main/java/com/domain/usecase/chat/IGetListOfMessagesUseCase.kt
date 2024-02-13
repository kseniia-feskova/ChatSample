package com.domain.usecase.chat

import com.domain.model.MessageUI
import kotlinx.coroutines.flow.Flow

interface IGetListOfMessagesUseCase {
    operator fun invoke(chatId: String): Flow<List<MessageUI>>

}