package com.domain.di

import com.domain.repository.IChatsRepository
import com.domain.repository.IMessagesRepository
import com.domain.repository.INewsRepository
import com.domain.repository.IUserRepository
import com.domain.usecase.IIsUserLoggedInUseCase
import com.domain.usecase.IsUserLoggedInUseCase
import com.domain.usecase.chat.CreateChatUseCase
import com.domain.usecase.chat.DeleteChatUseCase
import com.domain.usecase.chat.DeleteMessageUseCase
import com.domain.usecase.chat.GetAllChatsUseCase
import com.domain.usecase.chat.GetCompanionForChatUseCase
import com.domain.usecase.chat.GetCompanionsUseCase
import com.domain.usecase.chat.GetListOfMessagesUseCase
import com.domain.usecase.chat.ICreateChatUseCase
import com.domain.usecase.chat.IDeleteChatUseCase
import com.domain.usecase.chat.IDeleteMessageUseCase
import com.domain.usecase.chat.IGetAllChatsUseCase
import com.domain.usecase.chat.IGetCompanionForChatUseCase
import com.domain.usecase.chat.IGetCompanionsUseCase
import com.domain.usecase.chat.IGetListOfMessagesUseCase
import com.domain.usecase.chat.ISendMessageUseCase
import com.domain.usecase.chat.IUpdateUnreadChatUseCase
import com.domain.usecase.chat.SendMessageUseCase
import com.domain.usecase.chat.UpdateUnreadChatUseCase
import com.domain.usecase.login.CheckUsernameUseCase
import com.domain.usecase.login.CreateUserUseCase
import com.domain.usecase.login.ICheckUsernameUseCase
import com.domain.usecase.login.ICreateUserUseCase
import com.domain.usecase.login.ILogOutUseCase
import com.domain.usecase.login.ILoginUseCase
import com.domain.usecase.login.LogOutUseCase
import com.domain.usecase.login.LoginUseCase
import com.domain.usecase.news.GetNewsUseCase
import com.domain.usecase.news.IGetNewsUseCase
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