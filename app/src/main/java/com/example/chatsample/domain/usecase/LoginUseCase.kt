package com.example.chatsample.domain.usecase

import com.example.chatsample.data.encrypt
import com.example.chatsample.domain.model.UserUI
import com.example.chatsample.domain.repository.IUserRepository

class LoginUseCase(private val userRepository: IUserRepository) : ILoginUseCase {
    override suspend operator fun invoke(user: UserUI): Boolean {
        val userData = userRepository.getUserByName(encrypt(user.name))
        return userData?.password == encrypt(user.password)
    }
}