package com.example.chatsample.domain.model

import com.google.firebase.firestore.DocumentId

data class UserData(
    @DocumentId
    val id: String,
    val name: String,
    val password: String,
    val chats: Map<String, Boolean> = mapOf(),
    val img: String = "",
)
