package com.example.chatsample.domain.usecase

import com.example.chatsample.data.utils.encrypt
import com.example.chatsample.domain.model.UserUI
import com.example.chatsample.domain.repository.IUserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val userRepository: IUserRepository) : ILoginUseCase {
    override suspend operator fun invoke(user: UserUI): Boolean {
        val userData = userRepository.getUserByName(encrypt(user.name))
        val isUserLoggedIn = userData?.password == encrypt(user.password)
        if(isUserLoggedIn) userRepository.saveUsersIdLocally(userData?.id?:"")
        return isUserLoggedIn
    }
}