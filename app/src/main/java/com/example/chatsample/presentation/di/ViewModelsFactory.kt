package com.example.chatsample.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chatsample.domain.usecase.ICheckUsernameUseCase
import com.example.chatsample.domain.usecase.ICreateUserUseCase
import com.example.chatsample.presentation.LoginViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val checkUserName: ICheckUsernameUseCase,
    private val createUser: ICreateUserUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(checkUserName, createUser) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}