package com.example.chatsample.presentation.view.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.chatsample.R
import com.example.chatsample.domain.model.ChatUI
import com.example.chatsample.domain.model.UserUI
import com.example.chatsample.presentation.model.LoadListState
import com.example.chatsample.presentation.navigation.Screen
import com.example.chatsample.presentation.view.items.ChatPreviewItem
import com.example.chatsample.presentation.view.ui.theme.ChatSampleTheme
import com.example.chatsample.presentation.view.utils.BackButton
import com.example.chatsample.presentation.view.utils.BottomDrawerContent
import com.example.chatsample.presentation.view.utils.FloatingButtonContent
import com.example.chatsample.presentation.viewmodels.ChatsViewModel

@Composable
fun ChatsAndContactsScreen(
    navController: NavController? = null,
    getVmFactory: () -> ViewModelProvider.Factory,
    viewModel: ChatsViewModel = viewModel(factory = getVmFactory()),
) {
    viewModel.callAllChats()
    viewModel.callCompanions()
    ChatsAndContacts(
        navController,
        viewModel.listOfChats,
        viewModel.listOfCompanions,
    )
}

@Composable
fun ChatsAndContacts(
    navController: NavController? = null,
    listOfChats: LoadListState<ChatUI>,
    listOfCompanions: LoadListState<UserUI>
) {
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
            floatingActionButtonPosition = handleFabPosition(listOfChats),
            content = { paddings ->
                Column {
                    BackButton(
                        navController = navController,
                        modifier = Modifier.align(Alignment.Start)
                    )
                    when (listOfChats) {
                        is LoadListState.Success -> {
                            if (listOfChats.list.isEmpty()) {
                                EmptyListMessage()
                            } else {
                                ChatsScreenContent(
                                    listOfChats.list,
                                    paddings,
                                    navController
                                )
                            }
                        }

                        is LoadListState.Loading -> {
                            Log.e("ChatsAndContacts", "Loading")
                        }

                        is LoadListState.Error -> {
                            ErrorMessage(stringResource(id = R.string.error_internal))
                        }
                    }
                }
                Box() {
                    if (isDrawerVisible == DrawerValue.Open) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color(8, 25, 78, 65))
                        )
                    }
                }
            }
        )
        if (isDrawerVisible == DrawerValue.Open) {
            ContactsDrawer(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .wrapContentHeight(),
                navController = navController,
                listOfCompanions = listOfCompanions
            ) { changeDrawerVisibility() }
        }
    }
}

private fun handleFabPosition(listOfChats: LoadListState<ChatUI>): FabPosition {
    return if (listOfChats is LoadListState.Success) {
        if (listOfChats.list.isEmpty()) FabPosition.Center else FabPosition.End
    } else FabPosition.End
}

@Composable
fun EmptyListMessage() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .wrapContentSize(),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.empty_chats),
            style = MaterialTheme.typography.labelLarge,
            color = Color(10, 10, 100)
        )
    }
}

@Composable
fun ErrorMessage(text: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .wrapContentSize(),
            textAlign = TextAlign.Center,
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = Color(10, 10, 100)
        )
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
    modifier: Modifier,
    navController: NavController? = null,
    listOfCompanions: LoadListState<UserUI>,
    onCLose: () -> Unit
) {
    if (listOfCompanions is LoadListState.Success) {
        if (listOfCompanions.list.isEmpty()) {
            Card(
                modifier = modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(
                    CornerSize(30.dp),
                    CornerSize(30.dp),
                    CornerSize(0.dp),
                    CornerSize(0.dp)
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color(255, 255, 255, 255),
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                IconButton(
                    modifier = Modifier.align(Alignment.End),
                    onClick = { onCLose() },
                    content = {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null,
                            tint = Color(10, 10, 100)
                        )
                    })
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(0.dp, 0.dp, 0.dp, 32.dp)
                        .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.empty_new_companions),
                    style = MaterialTheme.typography.labelLarge,
                    color = Color(10, 10, 100)
                )
            }
        } else {
            BottomDrawerContent(
                modifier = modifier,
                items = listOfCompanions.list,
                onClose = { onCLose() },
                itemView = {
                    Text(
                        text = "- ${it.name}",
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                navController?.navigate("${Screen.CHAT.name}/${null}/${it.id}/${it.name}")
                            },
                        color = Color(10, 10, 100),
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
            )
        }
    }
}

@Composable
fun ChatsScreenContent(
    listOfChats: List<ChatUI>,
    paddings: PaddingValues,
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
            VerticalRecyclerView(listOfChats, navController)
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

@Preview(showBackground = true)
@Composable
fun ChatsScreenContentPreview() {
    ChatSampleTheme {
        ChatsAndContacts(
            listOfChats = LoadListState.Success(emptyList()),
            listOfCompanions = LoadListState.Success(emptyList())
        )
    }
}
