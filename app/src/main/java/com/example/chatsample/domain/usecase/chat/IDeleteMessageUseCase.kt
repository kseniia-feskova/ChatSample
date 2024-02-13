package com.example.chatsample.domain.usecase.chat

import com.example.chatsample.domain.model.MessageUI

interface IDeleteMessageUseCase {
    suspend operator fun invoke(message:MessageUI, chatId:String)

}