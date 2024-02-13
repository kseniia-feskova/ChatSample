package com.example.chatsample.data.di

import android.content.Context
import androidx.room.Room
import com.example.chatsample.data.db.NewsDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideNewsDB(context: Context): NewsDB {
        return Room.databaseBuilder(context, NewsDB::class.java, "NewsDB").build()
    }
}
