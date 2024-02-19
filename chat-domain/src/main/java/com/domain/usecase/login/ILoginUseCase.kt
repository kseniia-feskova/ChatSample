package com.domain.usecase.login

import com.domain.model.RegistrationUI

interface ILoginUseCase {
    suspend operator fun invoke(user: RegistrationUI): Boolean

}