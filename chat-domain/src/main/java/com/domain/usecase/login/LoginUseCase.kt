package com.domain.usecase.login

import com.domain.utils.encrypt
import com.domain.model.RegistrationUI
import com.domain.repository.IUserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val userRepository: IUserRepository) :
    ILoginUseCase {
    override suspend operator fun invoke(user: RegistrationUI): Boolean {
        val userData = userRepository.getUserByName(encrypt(user.name))
        val isUserLoggedIn = userData?.password == encrypt(user.password)
        if(isUserLoggedIn) userRepository.saveUsersIdLocally(userData?.id?:"")
        return isUserLoggedIn
    }
}