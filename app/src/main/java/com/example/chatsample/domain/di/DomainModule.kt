package com.example.chatsample.domain.di

import com.example.chatsample.domain.repository.IChatsRepository
import com.example.chatsample.domain.repository.IMessagesRepository
import com.example.chatsample.domain.repository.IUserRepository
import com.example.chatsample.domain.usecase.IIsUserLoggedInUseCase
import com.example.chatsample.domain.usecase.IsUserLoggedInUseCase
import com.example.chatsample.domain.usecase.chat.GetAllChatsUseCase
import com.example.chatsample.domain.usecase.chat.GetListOfMessagesUseCase
import com.example.chatsample.domain.usecase.chat.IGetAllChatsUseCase
import com.example.chatsample.domain.usecase.chat.IGetListOfMessagesUseCase
import com.example.chatsample.domain.usecase.login.CheckUsernameUseCase
import com.example.chatsample.domain.usecase.login.CreateUserUseCase
import com.example.chatsample.domain.usecase.login.ICheckUsernameUseCase
import com.example.chatsample.domain.usecase.login.ICreateUserUseCase
import com.example.chatsample.domain.usecase.login.ILoginUseCase
import com.example.chatsample.domain.usecase.login.LoginUseCase
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

    @Provides
    fun provideLoginUseCase(userRepository: IUserRepository): ILoginUseCase {
        return LoginUseCase(userRepository)
    }

    @Provides
    fun provideIsUserLoggedInUseCase(userRepository: IUserRepository): IIsUserLoggedInUseCase {
        return IsUserLoggedInUseCase(userRepository)
    }

    @Provides
    fun provideGetAllChatsUseCase(
        userRepository: IUserRepository,
        chatsRepository: IChatsRepository
    ): IGetAllChatsUseCase {
        return GetAllChatsUseCase(userRepository, chatsRepository)
    }

    @Provides
    fun provideGetListOfMessagesUseCase(
        userRepository: IUserRepository,
        messagesRepository: IMessagesRepository
    ): IGetListOfMessagesUseCase {
        return GetListOfMessagesUseCase(userRepository, messagesRepository)
    }
}