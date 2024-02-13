package com.domain.usecase.login

import com.domain.repository.IUserRepository

class LogOutUseCase(private val userRepository: IUserRepository) : ILogOutUseCase {
    override fun invoke() {
        userRepository.saveUsersIdLocally("")
    }
}