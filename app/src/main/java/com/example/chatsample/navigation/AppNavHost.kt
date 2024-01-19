package com.example.chatsample.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.chatsample.view.FirstScreenContent
import com.example.chatsample.view.SecondScreenContent

@Composable
fun AppNavHost(
    navController: NavHostController,
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
            SecondScreenContent(navController)
        }
    }
}
