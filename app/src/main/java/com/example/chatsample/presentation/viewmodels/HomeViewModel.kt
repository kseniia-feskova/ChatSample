package com.example.chatsample.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.chatsample.domain.usecase.IIsUserLoggedInUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val isUserLoggedIn: IIsUserLoggedInUseCase) :
    ViewModel() {
    fun isUserLoggedIn() = isUserLoggedIn.invoke()
}