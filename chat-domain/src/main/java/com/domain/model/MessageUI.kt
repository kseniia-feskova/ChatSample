package com.domain.model

import com.google.firebase.Timestamp

data class MessageUI(
    val id: String = "",
    val authorId: String = "",
    val authorName: String = "",
    val text: String = "",
    val isMyMessage: Boolean,
    val timestamp: Timestamp = Timestamp(0, 0),
)