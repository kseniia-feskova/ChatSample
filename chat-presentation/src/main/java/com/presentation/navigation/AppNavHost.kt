package com.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.chatsample.presentation.view.screens.ChatScreen
import com.example.chatsample.presentation.view.screens.ChatsAndContactsScreen
import com.example.chatsample.presentation.view.screens.HomeScreenContent
import com.example.chatsample.presentation.view.screens.LoginScreenContent
import com.example.chatsample.presentation.view.screens.MainScreen
import com.example.chatsample.presentation.view.screens.NewsScreen
import com.example.chatsample.presentation.view.screens.SignUpScreenContent

@Composable
fun AppNavHost(
    navController: NavHostController,
    viewModelFactory: ViewModelProvider.Factory,
    startDestination: String = NavigationItem.Home.route,
) {

    val getVmFactory: () -> ViewModelProvider.Factory = remember { { viewModelFactory } }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Home.route) {
            HomeScreenContent(navController, getVmFactory = getVmFactory)
        }
        composable(NavigationItem.Signup.route) {
            SignUpScreenContent(navController, getVmFactory = getVmFactory)
        }
        composable(NavigationItem.Login.route) {
            LoginScreenContent(navController, getVmFactory = getVmFactory)
        }

        composable(NavigationItem.Chats.route) {
            ChatsAndContactsScreen(navController, getVmFactory = getVmFactory)
        }

        composable(
            "${NavigationItem.Chat.route}/{chatId}/{companionId}/{companionName}",
            arguments = listOf(navArgument("chatId") { type = NavType.StringType; nullable = true },
                navArgument("companionId") { type = NavType.StringType; nullable = true },
                navArgument("companionName") { type = NavType.StringType; nullable = true }
                )
        ) { backStackEntry ->
            ChatScreen(
                chatId = backStackEntry.arguments?.getString("chatId") ?: "",
                companionId = backStackEntry.arguments?.getString("companionId") ?: "",
                companionName = backStackEntry.arguments?.getString("companionName") ?: "",
                getVmFactory = getVmFactory,
                navController = navController
            )
        }
        composable(NavigationItem.News.route) {
            NewsScreen(getVmFactory = getVmFactory, navController = navController)
        }
        composable(NavigationItem.Main.route) {
            MainScreen(getVmFactory = getVmFactory, navController = navController)
        }

    }
}
