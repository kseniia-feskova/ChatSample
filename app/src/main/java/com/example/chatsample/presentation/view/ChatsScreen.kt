package com.example.chatsample.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.chatsample.domain.model.ChatUI
import com.example.chatsample.presentation.ui.theme.ChatSampleTheme


@Composable
fun ChatsScreenContent(navController: NavController? = null) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(12.dp),
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp),
                text = "Chats",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(10, 10, 100)
            )
            VerticalRecyclerView(listOfTestChats.sortedBy { it.isRead })
        }
    }
}

@Composable
fun VerticalRecyclerView(items: List<ChatUI>) {
    LazyColumn(contentPadding = PaddingValues(vertical = 8.dp)) {
        items(items = items) {
            ChatPreviewItem(chat = it)
        }
    }
}

private val listOfTestChats = listOf(
    ChatUI("Kseniia", "My first kiss was with someOne special", false),
    ChatUI("Jugineee", "Hello, mario", true),
    ChatUI("Luigui", "Hi, it's me", true),
    ChatUI("Kseniia", "My first kiss was with someOne special", true),
    ChatUI("Jugineee", "Hello, mario", true),
    ChatUI("Luigui", "Hi, it's me", true),
    ChatUI("Kseniia", "My first kiss was with someOne special", false),
    ChatUI("Jugineee", "Hello, mario", true),
    ChatUI("Luigui", "Hi, it's me", true),
    ChatUI("Luigui", "Hi, it's me", false),
    ChatUI("Kseniia", "My first kiss was with someOne special", true),
    ChatUI("Jugineee", "Hello, mario", true),
    ChatUI("Luigui", "Hi, it's me", true)
)

@Preview(showBackground = true)
@Composable
fun ChatsScreenContentPreview() {
    ChatSampleTheme {
        ChatsScreenContent()
    }
}
