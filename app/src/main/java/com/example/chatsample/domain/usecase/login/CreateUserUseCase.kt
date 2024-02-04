package com.example.chatsample.domain.usecase.login

import com.example.chatsample.data.utils.encrypt
import com.example.chatsample.domain.model.UserData
import com.example.chatsample.domain.model.UserUI
import com.example.chatsample.domain.repository.IUserRepository
import java.util.*
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(private val userRepository: IUserRepository) :
    ICreateUserUseCase {
    override suspend operator fun invoke(user: UserUI) {
        val userData = mapUser(user)
        userRepository.setUser(userData)
        userRepository.saveUsersIdLocally(userData.id)
    }

    private fun mapUser(user: UserUI): UserData {
        return UserData(
            id = UUID.randomUUID().toString(),
            name = encrypt(user.name),
            password = encrypt(user.password)
        )
    }
}