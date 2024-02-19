package com.domain.usecase.chat


interface IDeleteChatUseCase {
    suspend operator fun invoke(chatId:String)

}