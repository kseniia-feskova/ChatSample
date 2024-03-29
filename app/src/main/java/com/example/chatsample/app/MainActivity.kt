package com.example.chatsample.app

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.chat_presentation.R
import com.example.chatsample.app.utils.NetworkStatusHelper
import com.presentation.navigation.AppNavHost
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
            content = { Text(stringResource(id = R.string.no_internet_connection)) })
    }
}

