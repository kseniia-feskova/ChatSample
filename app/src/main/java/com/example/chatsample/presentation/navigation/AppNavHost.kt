package com.example.chatsample.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.chatsample.presentation.di.ViewModelFactory
import com.example.chatsample.presentation.view.HomeScreenContent
import com.example.chatsample.presentation.view.LoginScreenContent
import com.example.chatsample.presentation.view.SignUpScreenContent
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
    }
}
