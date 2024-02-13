package com.example.chatsample.presentation.view.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.domain.model.ChatUI
import com.example.chatsample.presentation.navigation.NavigationItem
import com.example.chatsample.presentation.view.ui.theme.ChatSampleTheme
import com.google.firebase.Timestamp


@Composable
fun ChatPreviewItem(chat: ChatUI, navController: NavController? = null) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable {
                navController?.navigate("${NavigationItem.Chat.route}/${chat.id}/${null}/${chat.author}")
            },
        colors = CardDefaults.cardColors(
            containerColor = Color(-2, -2, -1),
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = chat.author,
                    style = typography.labelLarge,
                    color = Color(10, 10, 100)
                )
                Text(
                    text = chat.lastMessage,
                    style = typography.labelSmall,
                    color = Color(120, 200, 250)
                )
            }
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                if (!chat.isRead) {
                    UnreadIndicator()
                }
            }
        }
    }
}

@Composable
fun UnreadIndicator() {
    Box(
        modifier = Modifier
            .size(8.dp)
            .background(color = Color(10, 10, 100), shape = CircleShape)
    )
}

@Preview(showBackground = true)
@Composable
fun ChatPreviewItemPreview() {
    ChatSampleTheme {
        ChatPreviewItem(
            ChatUI("0", "Kseniia", "Hello, my friend it's me", false, Timestamp(0, 0)
            )
        )
    }
}