package com.example.chatsample.presentation.view.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.chatsample.domain.model.MessageUI
import com.example.chatsample.presentation.ui.theme.ChatSampleTheme
import com.google.firebase.Timestamp

@Composable
fun ChatScreen() {
    val items = testMessages.sortedBy { it.timestamp }
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(items = items) {
            if (it.isMyMessage) MyMessageItem(message = it)
            else CompanionMessageItem(message = it)
        }
    }
}

@Composable
fun CompanionMessageItem(message: MessageUI) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .widthIn(max = 80.percentWidth)
                .align(Alignment.CenterStart)
                .padding(horizontal = 8.dp, vertical = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (!message.isMyMessage) Color(10, 10, 100) else Color.White,
            ),
            elevation = CardDefaults.cardElevation(2.dp),
            shape = RoundedCornerShape(corner = CornerSize(16.dp))
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterStart)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Start),
                        text = message.authorName,
                        style = MaterialTheme.typography.labelLarge,
                        color = if (message.isMyMessage) Color(10, 10, 100) else Color.White
                    )
                    Text(
                        text = message.text,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(120, 200, 250)
                    )
                }
            }
        }
    }
}

@Composable
fun MyMessageItem(message: MessageUI) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .widthIn(max = 80.percentWidth)
                .align(Alignment.CenterEnd)
                .padding(horizontal = 8.dp, vertical = 8.dp),
            elevation = CardDefaults.cardElevation(2.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (!message.isMyMessage) Color(10, 10, 100) else Color.White,
            ),
            shape = RoundedCornerShape(corner = CornerSize(16.dp))
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterEnd)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.End),
                        text = message.authorName,
                        style = MaterialTheme.typography.labelLarge,
                        color = if (message.isMyMessage) Color(10, 10, 100) else Color.White
                    )
                    Text(
                        text = message.text,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(120, 200, 250)
                    )
                }
            }
        }
    }
}


//Custom Extension
val Int.percentWidth: Dp
    @Composable
    get() = (this / 100f * LocalConfiguration.current.screenWidthDp).dp


@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    ChatSampleTheme {
        ChatScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun CompanionMessageItemPreview() {
    ChatSampleTheme {
        CompanionMessageItem(
            MessageUI(
                id = "0",
                authorId = "09",
                authorName = "Kevin",
                isMyMessage = false,
                timestamp = Timestamp(1, 0),
                text = "Hello, my dear friend I am so glad to hear you are doing great at work "
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyMessageItemPreview() {
    ChatSampleTheme {
        MyMessageItem(
            MessageUI(
                id = "01",
                authorId = "091",
                authorName = "Olga",
                isMyMessage = true,
                timestamp = Timestamp(1, 0),
                text = "Hello, my dear friend"
            )
        )
    }
}


private val testMessages = listOf(
    MessageUI(
        id = "01",
        authorId = "091",
        authorName = "Olga",
        isMyMessage = true,
        timestamp = Timestamp(2, 0),
        text = "Hello, my dear friend I am so glad to hear you are doing great at work "
    ),
    MessageUI(
        id = "0",
        authorId = "09",
        authorName = "Kevin",
        isMyMessage = false,
        timestamp = Timestamp(1, 0),
        text = "Hello, my  you are doing great at work "
    ),
    MessageUI(
        id = "01",
        authorId = "091",
        authorName = "Olga",
        isMyMessage = true,
        timestamp = Timestamp(6, 0),
        text = "Hello, my dear friend I am so glad work "
    ),
    MessageUI(
        id = "0",
        authorId = "09",
        authorName = "Kevin",
        isMyMessage = false,
        timestamp = Timestamp(4, 0),
        text = "Hello, my jsncks ;slld "
    ),
    MessageUI(
        id = "01",
        authorId = "091",
        authorName = "Olga",
        isMyMessage = true,
        timestamp = Timestamp(3, 0),
        text = "Hello, my dear friend I am so glad to hear you are doing great atear you are doing grear you are doing great atear you are doing great at eat atear you are doing great atear you are doing great atear you are doing great atear you are doing great atear you are doing great atear you are doing great at work "
    ),
    MessageUI(
        id = "0",
        authorId = "09",
        authorName = "Kevin",
        isMyMessage = false,
        timestamp = Timestamp(5, 0),
        text = "Hello, my dear friend I am so glad to hear you are doing great at work ear you are doing great atear you are doing great atear you are doing great atear you are doing great at "
    )
)
