package com.example.chatsample.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chatsample.domain.usecase.login.ICheckUsernameUseCase
import com.example.chatsample.domain.usecase.login.ICreateUserUseCase
import com.example.chatsample.domain.usecase.IIsUserLoggedInUseCase
import com.example.chatsample.domain.usecase.login.ILoginUseCase
import com.example.chatsample.presentation.viewmodels.HomeViewModel
import com.example.chatsample.presentation.viewmodels.LoginViewModel
import com.example.chatsample.presentation.viewmodels.SignupViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(
    private val checkUserName: ICheckUsernameUseCase,
    private val createUser: ICreateUserUseCase,
    private val login: ILoginUseCase,
    private val isLoggedIn: IIsUserLoggedInUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            return SignupViewModel(checkUserName, createUser) as T
        }
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(login) as T
        }
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(isLoggedIn) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}