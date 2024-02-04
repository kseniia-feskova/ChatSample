package com.example.chatsample.domain.usecase.login

import com.example.chatsample.domain.model.UserUI

interface ILoginUseCase {
    suspend operator fun invoke(user: UserUI): Boolean

}