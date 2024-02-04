package com.example.chatsample.data.di

import com.example.chatsample.data.repository.UsersRepository
import com.example.chatsample.data.prefs.UserPreferences
import com.example.chatsample.domain.repository.IUserRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun provideUserRepository(userPreferences: UserPreferences): IUserRepository {
        return UsersRepository(userPreferences)
    }
}