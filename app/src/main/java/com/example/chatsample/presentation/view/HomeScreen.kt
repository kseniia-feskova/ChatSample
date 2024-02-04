package com.example.chatsample.presentation.view

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.chatsample.presentation.navigation.Screen
import com.example.chatsample.presentation.ui.theme.ChatSampleTheme
import com.example.chatsample.presentation.viewmodels.HomeViewModel

@Composable
fun HomeScreenContent(navController: NavController? = null, viewModel: HomeViewModel) {
    if (viewModel.isUserLoggedIn()) {
        //TODO change to chats
        navController?.navigate(Screen.SIGNUP.name)
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
                text = "Hello",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(10, 10, 100)
            )
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "VIEW DETAIL",
                style = MaterialTheme.typography.labelSmall,
                color = Color(120, 200, 250)
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
                Text(
                    text = "Log in", color = Color(-10, -10, -1)
                )
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
                    text = "Sign up", color = Color(-10, -10, -1)
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
