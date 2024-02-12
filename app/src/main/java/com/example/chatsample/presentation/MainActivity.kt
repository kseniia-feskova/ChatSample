package com.example.chatsample.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.chatsample.app.MyApplication
import com.example.chatsample.app.NetworkStatusHelper
import com.example.chatsample.presentation.navigation.AppNavHost
import com.example.chatsample.presentation.view.ui.theme.ChatSampleTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val isNetworkConnected = mutableStateOf(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApplication).appComponent.inject(this)

        setContent {
            NetworkStatusHelper(this).registerNetworkCallback {
                isNetworkConnected.value = it
            }
            Box() {
                ChatSampleTheme {
                    AppNavHost(navController = rememberNavController(), viewModelFactory)
                }
                if (!isNetworkConnected.value) {
                    DisabledInternetSnackbar(
                        modifier = Modifier.align(Alignment.BottomCenter)
                    )
                }
            }
        }
    }

    @Composable
    fun DisabledInternetSnackbar(modifier: Modifier) {
        Snackbar(modifier = modifier
            .padding(16.dp),
            containerColor = Color(10, 10, 100),
            content = { Text("Internet connection is missing") })
    }
}

