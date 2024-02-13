package com.domain.usecase.chat

import com.domain.model.ChatUI

interface IGetAllChatsUseCase {
    suspend operator fun invoke(): List<ChatUI>
}