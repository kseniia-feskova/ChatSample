package com.example.chatsample.presentation.model

sealed interface UserUiState {
    object Success : UserUiState
    data class Error(val message: String) : UserUiState
    object Loading : UserUiState
    object Empty : UserUiState
}