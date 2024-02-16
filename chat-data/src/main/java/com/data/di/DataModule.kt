package com.data.di

import com.data.api.ApiService
import com.data.api.IFirestoreService
import com.data.db.NewsDB
import com.example.chatsample.data.repository.UsersRepository
import com.example.chatsample.data.prefs.UserPreferences
import com.data.repository.ChatsRepository
import com.example.chatsample.data.repository.MessagesRepository
import com.example.chatsample.data.repository.NewsRepository
import com.domain.repository.IChatsRepository
import com.domain.repository.IMessagesRepository
import com.domain.repository.INewsRepository
import com.domain.repository.IUserRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun provideUserRepository(userPreferences: UserPreferences): IUserRepository {
        return UsersRepository(userPreferences)
    }

    @Provides
    fun provideChatsRepository(firestoreService: IFirestoreService): IChatsRepository {
        return ChatsRepository(firestoreService)
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