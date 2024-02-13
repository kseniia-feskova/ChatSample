package com.example.chatsample.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.domain.usecase.login.ILogOutUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val logoutUseCase: ILogOutUseCase
) : ViewModel() {
    fun onLogOut() {
        logoutUseCase.invoke()
    }
}