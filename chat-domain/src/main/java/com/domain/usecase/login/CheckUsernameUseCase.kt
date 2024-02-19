package com.domain.usecase.login

import com.domain.repository.IUserRepository
import com.domain.utils.encrypt
import javax.inject.Inject

class CheckUsernameUseCase @Inject constructor(private val userRepository: IUserRepository) :
    ICheckUsernameUseCase {
    override suspend fun invoke(name: String): Boolean {
        return userRepository.checkFreeName(encrypt(name))
    }
}