package com.example.chatsample.domain.usecase.login

import com.example.chatsample.domain.repository.IUserRepository
import com.example.chatsample.domain.utils.encrypt
import javax.inject.Inject

class CheckUsernameUseCase @Inject constructor(private val userRepository: IUserRepository) : ICheckUsernameUseCase {
    override suspend fun invoke(name: String): Boolean {
        return userRepository.checkFreeName(encrypt(name))
    }
}