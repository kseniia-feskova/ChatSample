package com.example.chatsample.domain.usecase.login

import com.example.chatsample.domain.repository.IUserRepository
import javax.inject.Inject

class CheckUsernameUseCase @Inject constructor(private val userRepository: IUserRepository) : ICheckUsernameUseCase {
    override suspend fun invoke(name: String): Boolean {
        return userRepository.checkFreeName(name)
    }
}