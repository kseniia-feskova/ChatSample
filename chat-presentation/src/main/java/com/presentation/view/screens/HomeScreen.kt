package com.example.chatsample.presentation.view.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.chat_presentation.R
import com.presentation.navigation.Screen
import com.example.chatsample.presentation.view.ui.theme.ChatSampleTheme
import com.example.chatsample.presentation.viewmodels.HomeViewModel

@Composable
fun HomeScreenContent(
    navController: NavController? = null,
    getVmFactory: () -> ViewModelProvider.Factory,
    viewModel: HomeViewModel = viewModel(factory = getVmFactory()),
) {
    if (viewModel.isUserLoggedIn()) {
        navController?.navigate(Screen.MAIN.name)
    } else {
        MainHomeScreenContent(navController)
    }
}

@Composable
fun MainHomeScreenContent(navController: NavController? = null) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(12.dp),
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp),
                text = stringResource(id = R.string.hello),
                style = MaterialTheme.typography.headlineMedium,
                color = Color(10, 10, 100)
            )
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(10, 10, 100)
                ),
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = { navController?.navigate(Screen.LOGIN.name) }
            ) {
                Text(text = stringResource(id = R.string.login), color = Color(-10, -10, -1))
            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(10, 10, 100)
                ),
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = { navController?.navigate(Screen.SIGNUP.name) }
            ) {
                Text(
                    text = stringResource(id = R.string.signup), color = Color(-10, -10, -1)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenContentPreview() {
    ChatSampleTheme {
        MainHomeScreenContent()
    }
}
