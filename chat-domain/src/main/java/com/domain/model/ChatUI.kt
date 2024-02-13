package com.domain.model

import com.google.firebase.Timestamp

data class ChatUI(
    val id: String,
    val author: String,
    val lastMessage: String = "",
    val isRead: Boolean,
    val timestamp: Timestamp
)
