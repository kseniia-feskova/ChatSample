package com.example.chatsample.domain.usecase.login

import com.example.chatsample.domain.model.RegistrationUI

interface ICreateUserUseCase {
    suspend operator fun invoke(user: RegistrationUI)
}