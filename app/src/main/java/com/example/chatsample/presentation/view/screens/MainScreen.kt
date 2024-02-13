package com.example.chatsample.presentation.view.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.chatsample.R
import com.example.chatsample.presentation.navigation.NavigationItem
import com.example.chatsample.presentation.navigation.Screen
import com.example.chatsample.presentation.viewmodels.MainViewModel

@Composable
fun MainScreen(
    getVmFactory: () -> ViewModelProvider.Factory,
    viewModel: MainViewModel = viewModel(factory = getVmFactory()),
    navController: NavController? = null
) {
    MainScreenContent(onLogOut = { viewModel.onLogOut() }, navController)
}

@Composable
fun MainScreenContent(onLogOut: () -> Unit, navController: NavController? = null) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(12.dp),
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(10, 10, 100)
                ),
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = { navController?.navigate(Screen.NEWS.name) }
            ) {
                Text(
                    text = stringResource(id = R.string.news), color = Color(-10, -10, -1)
                )
            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(10, 10, 100)
                ),
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = { navController?.navigate(Screen.CHATS.name) }
            ) {
                Text(
                    text = stringResource(id = R.string.chats), color = Color(-10, -10, -1)
                )
            }
        }
        IconButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            onClick = {
                onLogOut()
                navController?.navigate(NavigationItem.Home.route)
            }) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(id = R.string.logout),
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
                Icon(
                    tint = Color(10, 10, 100),
                    imageVector = Icons.Filled.ExitToApp,
                    contentDescription = stringResource(id = R.string.logout)
                )
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreenContent(onLogOut = {})
}