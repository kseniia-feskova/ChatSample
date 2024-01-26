package com.example.chatsample.app.di

import android.app.Application
import com.example.chatsample.presentation.MainActivity
import com.example.chatsample.data.di.DataModule
import com.example.chatsample.domain.di.DomainModule
import com.example.chatsample.presentation.LoginViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, DomainModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(application: Application): Builder

        fun build(): AppComponent
    }
    fun inject(application: Application)
    fun inject(activity: MainActivity)
    fun inject(loginViewModel: LoginViewModel)
}