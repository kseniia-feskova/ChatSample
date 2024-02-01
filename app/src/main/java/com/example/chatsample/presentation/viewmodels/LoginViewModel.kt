package com.example.chatsample.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatsample.domain.model.UserUI
import com.example.chatsample.domain.usecase.ILoginUseCase
import com.example.chatsample.presentation.model.UserUiState
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val login: ILoginUseCase
) : ViewModel() {

    var userCheck: UserUiState by mutableStateOf(UserUiState.Empty)
        private set

    fun checkAndLogin(name: String, password: String) {
        viewModelScope.launch {
            userCheck = UserUiState.Loading
            userCheck = try {
                val isLogin = login.invoke(UserUI(name, password))
                if(isLogin) UserUiState.Success
                else UserUiState.Error("Cannot login")

            } catch (e: IOException) {
                UserUiState.Error("Cannot send to back")
            }
        }
    }
}