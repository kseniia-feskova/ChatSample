package com.example.chatsample.domain.usecase.chat


interface IDeleteChatUseCase {
    suspend operator fun invoke(chatId:String)

}