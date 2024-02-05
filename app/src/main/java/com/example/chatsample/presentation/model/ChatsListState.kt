package com.example.chatsample.presentation.model

import com.example.chatsample.domain.model.ChatUI

sealed interface ChatsListState {
    data class Success(val chats: List<ChatUI>) : ChatsListState
    data class Error(val message: String) : ChatsListState
    data object Loading : ChatsListState
}