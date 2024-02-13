package com.domain.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId

data class ChatData(
    @DocumentId
    val id: String = "",
    val companions: List<String> = emptyList(),
    val timestamp: Timestamp = Timestamp(0, 0)
)

