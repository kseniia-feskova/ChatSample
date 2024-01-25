package com.example.chatsample.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.chatsample.app.MyApplication
import com.example.chatsample.presentation.di.ViewModelFactory
import com.example.chatsample.presentation.navigation.AppNavHost
import com.example.chatsample.presentation.ui.theme.ChatSampleTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApplication).appComponent.inject(this)

        setContent {
            ChatSampleTheme {
                AppNavHost(navController = rememberNavController(), viewModelFactory)
            }
        }
    }
}