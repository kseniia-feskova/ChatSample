package com.example.chatsample.domain.di

import com.example.chatsample.domain.repository.IChatsRepository
import com.example.chatsample.domain.repository.IMessagesRepository
import com.example.chatsample.domain.repository.INewsRepository
import com.example.chatsample.domain.repository.IUserRepository
import com.example.chatsample.domain.usecase.IIsUserLoggedInUseCase
import com.example.chatsample.domain.usecase.IsUserLoggedInUseCase
import com.example.chatsample.domain.usecase.chat.CreateChatUseCase
import com.example.chatsample.domain.usecase.chat.DeleteChatUseCase
import com.example.chatsample.domain.usecase.chat.DeleteMessageUseCase
import com.example.chatsample.domain.usecase.chat.GetAllChatsUseCase
import com.example.chatsample.domain.usecase.chat.GetCompanionForChatUseCase
import com.example.chatsample.domain.usecase.chat.GetCompanionsUseCase
import com.example.chatsample.domain.usecase.chat.GetListOfMessagesUseCase
import com.example.chatsample.domain.usecase.chat.ICreateChatUseCase
import com.example.chatsample.domain.usecase.chat.IDeleteChatUseCase
import com.example.chatsample.domain.usecase.chat.IDeleteMessageUseCase
import com.example.chatsample.domain.usecase.chat.IGetAllChatsUseCase
import com.example.chatsample.domain.usecase.chat.IGetCompanionForChatUseCase
import com.example.chatsample.domain.usecase.chat.IGetCompanionsUseCase
import com.example.chatsample.domain.usecase.chat.IGetListOfMessagesUseCase
import com.example.chatsample.domain.usecase.chat.ISendMessageUseCase
import com.example.chatsample.domain.usecase.chat.IUpdateUnreadChatUseCase
import com.example.chatsample.domain.usecase.chat.SendMessageUseCase
import com.example.chatsample.domain.usecase.chat.UpdateUnreadChatUseCase
import com.example.chatsample.domain.usecase.login.CheckUsernameUseCase
import com.example.chatsample.domain.usecase.login.CreateUserUseCase
import com.example.chatsample.domain.usecase.login.ICheckUsernameUseCase
import com.example.chatsample.domain.usecase.login.ICreateUserUseCase
import com.example.chatsample.domain.usecase.login.ILogOutUseCase
import com.example.chatsample.domain.usecase.login.ILoginUseCase
import com.example.chatsample.domain.usecase.login.LogOutUseCase
import com.example.chatsample.domain.usecase.login.LoginUseCase
import com.example.chatsample.domain.usecase.news.GetNewsUseCase
import com.example.chatsample.domain.usecase.news.IGetNewsUseCase
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

    @Provides
    fun provideGetCompanionsUseCase(
        userRepository: IUserRepository,
    ): IGetCompanionsUseCase {
        return GetCompanionsUseCase(userRepository)
    }

    @Provides
    fun provideCreateChatUseCase(
        userRepository: IUserRepository,
        chatsRepository: IChatsRepository
    ): ICreateChatUseCase {
        return CreateChatUseCase(userRepository, chatsRepository)
    }

    @Provides
    fun provideSendMessageUseCase(
        userRepository: IUserRepository,
        messagesRepository: IMessagesRepository
    ): ISendMessageUseCase {
        return SendMessageUseCase(userRepository, messagesRepository)
    }

    @Provides
    fun provideUpdateUnreadChatUseCase(userRepository: IUserRepository): IUpdateUnreadChatUseCase {
        return UpdateUnreadChatUseCase(userRepository)
    }

    @Provides
    fun provideGetCompanionForChatUseCase(
        userRepository: IUserRepository,
        chatsRepository: IChatsRepository
    ): IGetCompanionForChatUseCase {
        return GetCompanionForChatUseCase(userRepository, chatsRepository)
    }

    @Provides
    fun provideLogOutUseCase(userRepository: IUserRepository): ILogOutUseCase {
        return LogOutUseCase(userRepository)
    }


    @Provides
    fun provideGetNewsUseCase(newsRepository: INewsRepository): IGetNewsUseCase {
        return GetNewsUseCase(newsRepository)
    }

    @Provides
    fun provideDeleteChatUseCase(
        chatsRepository: IChatsRepository,
        userRepository: IUserRepository
    ): IDeleteChatUseCase {
        return DeleteChatUseCase(chatsRepository, userRepository)
    }
    @Provides
    fun provideDeleteMessageUseCase(
        chatsRepository: IChatsRepository,
    ): IDeleteMessageUseCase {
        return DeleteMessageUseCase(chatsRepository)
    }
}