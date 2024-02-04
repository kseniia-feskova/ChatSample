package com.example.chatsample.domain.usecase.login

import com.example.chatsample.domain.model.UserUI

interface ICreateUserUseCase {
    suspend operator fun invoke(user: UserUI)
}