package com.example.chatsample.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.chatsample.presentation.di.ViewModelFactory
import com.example.chatsample.presentation.view.screens.ChatScreen
import com.example.chatsample.presentation.view.screens.ChatsAndContactsScreen
import com.example.chatsample.presentation.view.screens.HomeScreenContent
import com.example.chatsample.presentation.view.screens.LoginScreenContent
import com.example.chatsample.presentation.view.screens.SignUpScreenContent
import com.example.chatsample.presentation.viewmodels.ChatViewModel
import com.example.chatsample.presentation.viewmodels.ChatsViewModel
import com.example.chatsample.presentation.viewmodels.HomeViewModel
import com.example.chatsample.presentation.viewmodels.LoginViewModel
import com.example.chatsample.presentation.viewmodels.SignupViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    viewModelFactory: ViewModelFactory,
    startDestination: String = NavigationItem.Home.route,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Home.route) {
            val localViewModelStoreOwner = LocalViewModelStoreOwner.current
            if (localViewModelStoreOwner != null) {
                val homeViewModel: HomeViewModel = viewModel(
                    viewModelStoreOwner = localViewModelStoreOwner,
                    factory = viewModelFactory
                )
                HomeScreenContent(navController, homeViewModel)
            }
        }
        composable(NavigationItem.Signup.route) {
            val localViewModelStoreOwner = LocalViewModelStoreOwner.current
            if (localViewModelStoreOwner != null) {
                val signupViewModel: SignupViewModel = viewModel(
                    viewModelStoreOwner = localViewModelStoreOwner,
                    factory = viewModelFactory
                )
                SignUpScreenContent(navController, signupViewModel)
            }
        }
        composable(NavigationItem.Login.route) {
            val localViewModelStoreOwner = LocalViewModelStoreOwner.current
            if (localViewModelStoreOwner != null) {
                val loginViewModel: LoginViewModel = viewModel(
                    viewModelStoreOwner = localViewModelStoreOwner,
                    factory = viewModelFactory
                )
                LoginScreenContent(navController, loginViewModel)
            }
        }

        composable(NavigationItem.Chats.route) {
            val localViewModelStoreOwner = LocalViewModelStoreOwner.current
            if (localViewModelStoreOwner != null) {
                val chatsViewModel: ChatsViewModel = viewModel(
                    viewModelStoreOwner = localViewModelStoreOwner,
                    factory = viewModelFactory
                )
                ChatsAndContactsScreen(chatsViewModel, navController)
            }
        }

        composable(
            "${NavigationItem.Chat.route}/{chatId}/{companionId}",
            arguments = listOf(navArgument("chatId") { type = NavType.StringType; nullable = true },
                navArgument("companionId") { type = NavType.StringType; nullable = true })
        ) { backStackEntry ->
            val localViewModelStoreOwner = LocalViewModelStoreOwner.current
            if (localViewModelStoreOwner != null) {
                val chatViewModel: ChatViewModel = viewModel(
                    viewModelStoreOwner = localViewModelStoreOwner,
                    factory = viewModelFactory
                )
                ChatScreen(
                    chatId = backStackEntry.arguments?.getString("chatId") ?: "",
                    companionId = backStackEntry.arguments?.getString("companionId") ?: "",
                    viewModel = chatViewModel,
                    navController = navController
                )
            }
        }
    }
}
