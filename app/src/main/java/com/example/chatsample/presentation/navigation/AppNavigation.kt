package com.example.chatsample.presentation.navigation

enum class Screen {
    HOME,
    LOGIN,
    SIGNUP,
    CHATS
}

sealed class NavigationItem(val route: String) {
    data object Home : NavigationItem(Screen.HOME.name)
    data object Login : NavigationItem(Screen.LOGIN.name)
    data object Signup : NavigationItem(Screen.SIGNUP.name)
    data object Chats : NavigationItem(Screen.CHATS.name)
}