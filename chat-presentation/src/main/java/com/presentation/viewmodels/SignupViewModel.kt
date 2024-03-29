package com.example.chatsample.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domain.model.RegistrationUI
import com.domain.usecase.login.ICheckUsernameUseCase
import com.domain.usecase.login.ICreateUserUseCase
import com.presentation.model.UserUiState
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class SignupViewModel @Inject constructor(
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
            val user = RegistrationUI(name, password)
            createUser.invoke(user)
            UserUiState.Success
        } catch (e: IOException) {
            UserUiState.Error("Cannot create user")
        }
    }
}