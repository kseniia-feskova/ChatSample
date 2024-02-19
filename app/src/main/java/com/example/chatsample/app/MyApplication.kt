package com.example.chatsample.app

import android.app.Application
import com.chibatching.kotpref.Kotpref
import com.example.chatsample.app.di.AppComponent
import com.example.chatsample.app.di.DaggerAppComponent

class MyApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        Kotpref.init(applicationContext)
        appComponent = DaggerAppComponent.factory().create(this)
        appComponent.inject(this)
    }
}