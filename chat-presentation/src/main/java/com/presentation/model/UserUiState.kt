package com.presentation.model

sealed interface UserUiState {
    data object Success : UserUiState
    data class Error(val message: String) : UserUiState
    data object Loading : UserUiState
    data object Empty : UserUiState
}