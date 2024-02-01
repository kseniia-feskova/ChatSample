package com.example.chatsample.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chatsample.domain.usecase.ICheckUsernameUseCase
import com.example.chatsample.domain.usecase.ICreateUserUseCase
import com.example.chatsample.domain.usecase.ILoginUseCase
import com.example.chatsample.presentation.viewmodels.LoginViewModel
import com.example.chatsample.presentation.viewmodels.SignupViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val checkUserName: ICheckUsernameUseCase,
    private val createUser: ICreateUserUseCase,
    private val login: ILoginUseCase,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            return SignupViewModel(checkUserName, createUser) as T
        }
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(login) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}