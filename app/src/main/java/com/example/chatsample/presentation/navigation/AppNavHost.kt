package com.example.chatsample.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.chatsample.presentation.LoginViewModel
import com.example.chatsample.presentation.di.ViewModelFactory
import com.example.chatsample.presentation.view.FirstScreenContent
import com.example.chatsample.presentation.view.SecondScreenContent

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
            FirstScreenContent(navController)
        }
        composable(NavigationItem.Login.route) {
            val localViewModelStoreOwner = LocalViewModelStoreOwner.current
            if (localViewModelStoreOwner != null) {
                val loginViewModel: LoginViewModel = viewModel(
                    viewModelStoreOwner = localViewModelStoreOwner,
                    factory = viewModelFactory
                )
                SecondScreenContent(navController, loginViewModel)
            }
        }
    }
}
