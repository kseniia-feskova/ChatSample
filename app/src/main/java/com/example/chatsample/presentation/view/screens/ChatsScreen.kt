package com.example.chatsample.presentation.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.chatsample.domain.model.ChatUI
import com.example.chatsample.presentation.navigation.Screen
import com.example.chatsample.presentation.ui.theme.ChatSampleTheme
import com.example.chatsample.presentation.view.ChatPreviewItem
import com.example.chatsample.presentation.view.utils.BottomDrawerContent
import com.example.chatsample.presentation.view.utils.FloatingButtonContent

@Composable
fun ChatsAndContactsScreen(navController: NavController? = null) {
    //val listOfContacts = viewModel.getAllUsersWithoutMe(name)
    //val listOfChats = viewModel.getAllChats()
    var isDrawerVisible by remember { mutableStateOf(DrawerValue.Closed) }
    fun changeDrawerVisibility() {
        isDrawerVisible = if (isDrawerVisible == DrawerValue.Open) DrawerValue.Closed
        else DrawerValue.Open
    }
    Box() {
        Scaffold(
            floatingActionButton = {
                if (isDrawerVisible == DrawerValue.Closed) {
                    ChatsFloatingButton() { changeDrawerVisibility() }
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            content = { paddings ->
                ChatsScreenContent(paddings, isDrawerVisible, navController)
            }
        )
        if (isDrawerVisible == DrawerValue.Open) {
            ContactsDrawer(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .wrapContentHeight(),
                navController = navController,
            ) { changeDrawerVisibility() }
        }
    }
}

@Composable
fun ChatsFloatingButton(onClick: () -> Unit) {
    FloatingButtonContent(
        modifier = Modifier
            .padding(24.dp)
            .size(64.dp)
            .background(color = Color(10, 10, 100), shape = CircleShape)
    ) { onClick() }
}

@Composable
fun ContactsDrawer(
    modifier: Modifier, navController: NavController? = null, onCLose: () -> Unit
) {
    BottomDrawerContent(
        modifier = modifier,
        items = listOf("Selectial", "Cortney", "Fibie", "Ross", "Qwerty", "Poiuyt"),
        onClose = { onCLose() },
        itemView = {
            Text(
                text = "- $it",
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        navController?.navigate(Screen.CHAT.name)
                    },
                color = Color(10, 10, 100),
                style = MaterialTheme.typography.labelMedium,

                )
        }
    )
}

@Composable
fun ChatsScreenContent(
    paddings: PaddingValues,
    isDrawerVisible: DrawerValue,
    navController: NavController? = null
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddings)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(12.dp),
        ) {
            VerticalRecyclerView(listOfTestChats.sortedBy { it.isRead }, navController)
        }
        if (isDrawerVisible == DrawerValue.Open) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(8, 25, 78, 65))
            )
        }
    }
}

@Composable
fun VerticalRecyclerView(items: List<ChatUI>, navController: NavController? = null) {
    LazyColumn(contentPadding = PaddingValues(vertical = 8.dp)) {
        items(items = items) {
            ChatPreviewItem(chat = it, navController = navController)
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
        ChatsAndContactsScreen()
    }
}
