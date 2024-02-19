package com.example.chatsample.data.di

import com.example.chatsample.data.prefs.UserPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class KotprefModule {
    @Provides
    @Singleton
    fun provideUserPreferences(): UserPreferences {
        return UserPreferences()
    }
}