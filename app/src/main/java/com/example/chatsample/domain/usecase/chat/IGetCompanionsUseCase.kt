package com.example.chatsample.domain.usecase.chat

import com.example.chatsample.domain.model.UserUI

interface IGetCompanionsUseCase {

    suspend operator fun invoke(): List<UserUI>

}