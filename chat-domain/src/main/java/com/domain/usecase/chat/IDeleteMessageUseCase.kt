package com.domain.usecase.chat

import com.domain.model.MessageUI

interface IDeleteMessageUseCase {
    suspend operator fun invoke(message: MessageUI, chatId:String)

}