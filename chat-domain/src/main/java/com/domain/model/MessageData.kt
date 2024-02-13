package com.domain.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId

data class MessageData(
    @DocumentId
    val id: String = "",
    val authorId: String = "",
    val text: String = "",
    val timestamp: Timestamp = Timestamp(0, 0),
)