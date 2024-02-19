package com.example.chatsample.app.di

import android.app.Application
import android.content.Context
import com.data.di.DataModule
import com.example.chatsample.data.di.DatabaseModule
import com.example.chatsample.data.di.KotprefModule
import com.example.chatsample.data.di.NetworkModule
import com.domain.di.DomainModule
import com.example.chatsample.app.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, DomainModule::class, KotprefModule::class, NetworkModule::class, ViewModelsModule::class, DatabaseModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
    fun inject(application: Application)
    fun inject(activity: MainActivity)
}