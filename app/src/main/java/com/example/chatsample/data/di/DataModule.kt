package com.example.chatsample.data.di

import com.example.chatsample.data.api.ApiService
import com.example.chatsample.data.db.NewsDB
import com.example.chatsample.data.repository.UsersRepository
import com.example.chatsample.data.prefs.UserPreferences
import com.example.chatsample.data.repository.ChatsRepository
import com.example.chatsample.data.repository.MessagesRepository
import com.example.chatsample.data.repository.NewsRepository
import com.example.chatsample.domain.repository.IChatsRepository
import com.example.chatsample.domain.repository.IMessagesRepository
import com.example.chatsample.domain.repository.INewsRepository
import com.example.chatsample.domain.repository.IUserRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun provideUserRepository(userPreferences: UserPreferences): IUserRepository {
        return UsersRepository(userPreferences)
    }

    @Provides
    fun provideChatsRepository(): IChatsRepository {
        return ChatsRepository()
    }

    @Provides
    fun provideMessagesRepository(chatsRepository: IChatsRepository): IMessagesRepository {
        return MessagesRepository(chatsRepository)
    }

    @Provides
    fun provideNewsRepository(newsApiService: ApiService, newsDB: NewsDB): INewsRepository {
        return NewsRepository(newsApiService, newsDB)
    }
}