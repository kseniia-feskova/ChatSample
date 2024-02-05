package com.example.chatsample.domain.usecase.login

import com.example.chatsample.domain.model.RegistrationUI

interface ILoginUseCase {
    suspend operator fun invoke(user: RegistrationUI): Boolean

}