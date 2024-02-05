package com.example.chatsample.domain.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId

/*
*  Chats collection ---> Messages subCollection
*  1 chat ---> Many messages
*
*  Flow #1 (there is no chat):
*  1. Create message. Get text and timestamp, add author (current user), set with new id
*  2. After creation the object of the message, create chat. Add companionsIds (current + companion)
*  3. Add chat to database, save message in chats subCollection of messages
*  4. Update users map of chats. Add value <String (chats id)> - <Boolean (is chat read)>

*  Flow #2 (there is a chat):
*  1. We need to get an id of chat. Use chats from current and filter with companions id
*  2. Save message in chats subCollection of messages
*  3. Update users map of chats. Add value <String (chats id)> - <Boolean (is chat read)>
* */
data class ChatData(
    @DocumentId
    val id: String = "",
    val companions: List<String> = emptyList(),
    val timestamp: Timestamp = Timestamp(0, 0)
)

