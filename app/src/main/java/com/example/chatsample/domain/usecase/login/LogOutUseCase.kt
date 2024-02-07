package com.example.chatsample.domain.usecase.login

import com.example.chatsample.domain.repository.IUserRepository

class LogOutUseCase(private val userRepository: IUserRepository) : ILogOutUseCase {
    override fun invoke() {
        userRepository.saveUsersIdLocally("")
    }
}