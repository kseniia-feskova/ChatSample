package com.example.chatsample.domain.usecase

import com.example.chatsample.data.encrypt
import com.example.chatsample.domain.repository.IUserRepository
import com.example.chatsample.domain.model.UserData
import com.example.chatsample.domain.model.UserUI
import java.util.*

class CreateUserUseCase(private val userRepository: IUserRepository) : ICreateUserUseCase {
    override suspend operator fun invoke(user: UserUI) {
        val userData = mapUser(user)
        userRepository.setUser(userData)
    }

    private fun mapUser(user: UserUI): UserData {
        return UserData(
            id = UUID.randomUUID().toString(),
            name = encrypt(user.name),
            password = encrypt(user.password)
        )
    }
}