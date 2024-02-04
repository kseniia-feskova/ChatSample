package com.example.chatsample.domain.usecase.login

interface ICheckUsernameUseCase {
    suspend operator fun invoke(name: String): Boolean

}