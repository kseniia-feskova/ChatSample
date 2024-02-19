package com.domain.usecase.login

import com.domain.model.RegistrationUI

interface ICreateUserUseCase {
    suspend operator fun invoke(user: RegistrationUI)
}