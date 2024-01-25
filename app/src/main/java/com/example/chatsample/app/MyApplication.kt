package com.example.chatsample.app

import android.app.Application
import com.example.chatsample.app.di.AppComponent
import com.example.chatsample.app.di.DaggerAppComponent

class MyApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .applicationContext(this)
            .build()

        appComponent.inject(this)
    }
}