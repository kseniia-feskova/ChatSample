package com.example.chatsample.domain.usecase

interface ICheckUsernameUseCase {
    suspend operator fun invoke(name: String): Boolean

}