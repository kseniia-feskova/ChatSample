package com.example.chatsample.presentation.model

sealed interface LoadListState<out T> {
    data class Success<T>(val list: List<T>) : LoadListState<T>
    data class Error(val message: String) : LoadListState<Nothing>
    data object Loading : LoadListState<Nothing>
}