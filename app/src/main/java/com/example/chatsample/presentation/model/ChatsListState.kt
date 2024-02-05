package com.example.chatsample.presentation.model

import com.example.chatsample.domain.model.ChatUI
import com.example.chatsample.domain.model.UserUI

sealed interface ChatsListState {
    data class Success(val chats: List<ChatUI>) : ChatsListState
    data class Error(val message: String) : ChatsListState
    data object Loading : ChatsListState
}

sealed interface UsersListState {
    data class Success(val chats: List<UserUI>) : UsersListState
    data class Error(val message: String) : UsersListState
    data object Loading : UsersListState
}