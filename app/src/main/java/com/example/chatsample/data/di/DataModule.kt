package com.example.chatsample.data.di

import com.example.chatsample.data.UsersRepository
import com.example.chatsample.domain.repository.IUserRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun provideUserRepository(): IUserRepository {
        return UsersRepository()
    }
}