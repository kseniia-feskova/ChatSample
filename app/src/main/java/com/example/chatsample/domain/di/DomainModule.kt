package com.example.chatsample.domain.di

import com.example.chatsample.domain.repository.IUserRepository
import com.example.chatsample.domain.usecase.CheckUsernameUseCase
import com.example.chatsample.domain.usecase.CreateUserUseCase
import com.example.chatsample.domain.usecase.ICheckUsernameUseCase
import com.example.chatsample.domain.usecase.ICreateUserUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideCheckUsernameUseCase(userRepository: IUserRepository): ICheckUsernameUseCase {
        return CheckUsernameUseCase(userRepository)
    }

    @Provides
    fun provideCreateUserUseCase(userRepository: IUserRepository): ICreateUserUseCase {
        return CreateUserUseCase(userRepository)
    }

}