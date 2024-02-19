package com.domain.usecase

import com.domain.repository.IUserRepository
import javax.inject.Inject

class IsUserLoggedInUseCase @Inject constructor(private val usersRepository: IUserRepository) :
    IIsUserLoggedInUseCase {
    override fun invoke(): Boolean {
        val currentId = usersRepository.getLoggedId()
        return currentId.isNotEmpty()
    }
}