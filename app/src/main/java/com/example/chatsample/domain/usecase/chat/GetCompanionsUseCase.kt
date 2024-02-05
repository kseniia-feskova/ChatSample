package com.example.chatsample.domain.usecase.chat

import com.example.chatsample.domain.model.UserData
import com.example.chatsample.domain.model.UserUI
import com.example.chatsample.domain.repository.IUserRepository
import com.example.chatsample.domain.utils.decrypt
import javax.inject.Inject

class GetCompanionsUseCase @Inject constructor(private val usersRepository: IUserRepository) :
    IGetCompanionsUseCase {
    override suspend fun invoke(): List<UserUI> {
        val userData = usersRepository.getNewCompanions()
        return userData.map {
            mapUserToUI(it)
        }
    }

    private fun mapUserToUI(userData: UserData): UserUI {
        return UserUI(
            userData.id,
            decrypt(userData.name)
        )
    }
}