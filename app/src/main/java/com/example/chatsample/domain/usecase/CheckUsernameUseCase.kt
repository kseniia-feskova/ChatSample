package com.example.chatsample.domain.usecase

import com.example.chatsample.domain.repository.IUserRepository

class CheckUsernameUseCase(private val userRepository: IUserRepository) : ICheckUsernameUseCase {
    override suspend fun invoke(name: String): Boolean {
        return userRepository.checkFreeName(name)
    }
}