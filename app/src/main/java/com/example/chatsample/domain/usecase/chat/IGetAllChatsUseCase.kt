package com.example.chatsample.domain.usecase.chat

import com.example.chatsample.domain.model.ChatUI

interface IGetAllChatsUseCase {

    suspend operator fun invoke(): List<ChatUI>
}