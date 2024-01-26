package com.example.chatsample.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatsample.domain.model.UserUI
import com.example.chatsample.domain.usecase.ICheckUsernameUseCase
import com.example.chatsample.domain.usecase.ICreateUserUseCase
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val checkUserName: ICheckUsernameUseCase,
    private val createUser: ICreateUserUseCase
) : ViewModel() {
    var userCreation: UserUiState by mutableStateOf(UserUiState.Empty)
        private set

    fun checkAndCreateUser(name: String, password: String) {
        viewModelScope.launch {
            userCreation = UserUiState.Loading
            userCreation = try {
                val isNameFree = checkFreeName(name)
                if (isNameFree) {
                    createUser(name, password)
                } else UserUiState.Error("The name is set")
            } catch (e: IOException) {
                UserUiState.Error("Cannot create and check user")
            }
        }
    }

    private suspend fun checkFreeName(name: String): Boolean {
        return checkUserName.invoke(name)
    }

    private suspend fun createUser(name: String, password: String): UserUiState {
        return try {
            val user = UserUI(name, password)
            createUser.invoke(user)
            UserUiState.Success
        } catch (e: IOException) {
            UserUiState.Error("Cannot create user")
        }
    }
}

sealed interface UserUiState {
    data object Success : UserUiState
    data class Error(val message: String) : UserUiState
    data object Loading : UserUiState
    data object Empty : UserUiState
}