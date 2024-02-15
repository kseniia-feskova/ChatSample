package com.presentation.navigation

enum class Screen {
    HOME,
    LOGIN,
    SIGNUP,
    CHATS,
    CHAT,
    NEWS,
    MAIN
}

sealed class NavigationItem(val route: String) {
    data object Home : NavigationItem(Screen.HOME.name)
    data object Login : NavigationItem(Screen.LOGIN.name)
    data object Signup : NavigationItem(Screen.SIGNUP.name)
    data object Chats : NavigationItem(Screen.CHATS.name)
    data object Chat : NavigationItem(Screen.CHAT.name)
    data object News : NavigationItem(Screen.NEWS.name)
    data object Main : NavigationItem(Screen.MAIN.name)
}