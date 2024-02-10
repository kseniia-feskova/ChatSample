package com.example.chatsample.data.di

import com.example.chatsample.data.api.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideNewsApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.currentsapi.services/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }
}