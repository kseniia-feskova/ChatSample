package com.example.chatsample.presentation.view.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.chatsample.domain.model.MessageUI
import com.example.chatsample.presentation.model.LoadListState
import com.example.chatsample.presentation.view.ui.theme.Blue50
import com.example.chatsample.presentation.view.ui.theme.ChatSampleTheme
import com.example.chatsample.presentation.view.ui.theme.WhiteBlue
import com.example.chatsample.presentation.view.utils.BackButton
import com.example.chatsample.presentation.viewmodels.ChatViewModel
import com.google.firebase.Timestamp

@Composable
fun ChatScreen(
    chatId: String,
    companionId: String,
    companionName: String,
    getVmFactory: () -> ViewModelProvider.Factory,
    viewModel: ChatViewModel = viewModel(factory = getVmFactory()),
    navController: NavController
) {
    if (companionId.isNotEmpty()) {
        viewModel.setCompanionId(companionId)
    }
    if (chatId.isNotEmpty()) {
        Log.e("ChatScreen", "chatId = $chatId")
        viewModel.setChatId(chatId)
        viewModel.updateChatAsRead()
    }
    ChatScreenContent(
        loadMessages = viewModel.messagesList,
        companionName = companionName,
        navController = navController,
        sendMessage = {
            viewModel.sendNewMessage(it)
        },
        deleteChat = {
            viewModel.deleteChat()
        },
        deleteMessage = { message ->
            viewModel.deleteMessage(message)
        }
    )
}

@Composable
fun ChatScreenContent(
    loadMessages: LoadListState<MessageUI>,
    companionName: String,
    navController: NavController? = null,
    sendMessage: (String) -> Unit,
    deleteChat: () -> Unit,
    deleteMessage: (MessageUI) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        ChatTopBar(
            modifier = Modifier.align(Alignment.TopStart),
            companionName,
            navController,
            deleteChat
        )
        HandleListOfMessages(loadMessages, deleteMessage)
        SendMessageContainer(
            modifier = Modifier.align(Alignment.BottomCenter),
            sendMessage
        )
    }
}

@Composable
fun ChatTopBar(
    modifier: Modifier,
    companionName: String,
    navController: NavController? = null,
    deleteChat: () -> Unit
) {
    var expandedMenu by remember { mutableStateOf(false) }
    Row(
        modifier = modifier
            .background(Color.White)
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        BackButton(
            navController,
            modifier = Modifier
                .weight(1f)
                .padding(0.dp, 0.dp, 24.dp, 0.dp)
        )
        Text(
            text = companionName,
            modifier = Modifier
                .wrapContentHeight()
                .padding(12.dp)
                .weight(2f),
            color = Color(10, 10, 100),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )

        Icon(
            modifier = Modifier
                .weight(1f)
                .padding(56.dp, 0.dp, 0.dp, 0.dp)
                .clickable {
                    expandedMenu = true
                },
            imageVector = Icons.Default.MoreVert,
            contentDescription = "Menu",
            tint = Color(10, 10, 100),
        )

        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            DropdownMenu(
                expanded = expandedMenu,
                onDismissRequest = { expandedMenu = false }
            ) {
                DropdownMenuItem(text = { Text("Delete") }, onClick = {
                    deleteChat()
                    onBackClick(navController)
                })
            }
        }
    }
}

@Composable
fun HandleListOfMessages(
    loadMessages: LoadListState<MessageUI>,
    deleteMessage: (MessageUI) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (loadMessages is LoadListState.Success) {
            val listState = rememberLazyListState()
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(vertical = 56.dp),
                reverseLayout = false,
                contentPadding = PaddingValues(vertical = 8.dp),
                state = listState
            ) {
                items(items = loadMessages.list.sortedBy { it.timestamp }) {
                    if (it.isMyMessage) MyMessageItem(
                        message = it,
                        deleteMessage = deleteMessage
                    )
                    else CompanionMessageItem(message = it)
                }
            }
            LaunchedEffect(Unit) {
                if (loadMessages.list.isNotEmpty()) {
                    listState.scrollToItem(loadMessages.list.size)
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SendMessageContainer(modifier: Modifier, sendMessage: (String) -> Unit) {
    var messageText by remember { mutableStateOf(TextFieldValue()) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        value = messageText,
        onValueChange = { messageText = it },
        singleLine = false,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        placeholder = {
            Text(
                "New message",
                modifier = Modifier.padding(vertical = 4.dp)
            )
        },
        shape = RoundedCornerShape(
            CornerSize(16.dp),
            CornerSize(16.dp),
            CornerSize(0.dp),
            CornerSize(0.dp)
        ),
        colors = newMessageColors(),
        trailingIcon = {
            IconButton(
                modifier = Modifier.padding(0.dp),
                onClick = {
                    if (messageText.text.trim().isNotEmpty()) {
                        sendMessage(messageText.text)
                        messageText = TextFieldValue()
                        focusRequester.freeFocus()
                        keyboardController?.hide()
                    }
                }) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    modifier = Modifier
                        .padding(0.dp)
                        .size(32.dp),
                    tint = Color(10, 10, 100),
                    contentDescription = ""
                )
            }
        }
    )
}

@Composable
private fun newMessageColors() = OutlinedTextFieldDefaults.colors(
    unfocusedTextColor = WhiteBlue,
    focusedTextColor = Blue50,
    unfocusedBorderColor = Color.Transparent,
    focusedBorderColor = Color.Transparent,
    unfocusedContainerColor = Color.White,
    focusedContainerColor = Color.White,
    focusedPlaceholderColor = Color(129, 129, 158, 255),
    unfocusedPlaceholderColor = Color(10, 10, 100)
)

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
fun MyMessageItem(message: MessageUI, deleteMessage: (MessageUI) -> Unit) {
    var isLongPressed by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        isLongPressed = true
                    }
                )
            }
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
                if (isLongPressed) {
                    DropdownMenu(
                        expanded = isLongPressed,
                        onDismissRequest = { isLongPressed = false },
                    ) {
                        DropdownMenuItem(text = { Text("Delete messsage") }, onClick = {
                            deleteMessage(message)
                            isLongPressed = false
                        })
                    }
                }
            }
        }
    }
}


val Int.percentWidth: Dp
    @Composable
    get() = (this / 100f * LocalConfiguration.current.screenWidthDp).dp


@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    ChatSampleTheme {
        ChatScreenContent(
            LoadListState.Success(testList),
            companionName = "Kevin",
            sendMessage = {},
            deleteChat = {},
            deleteMessage = {})

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
        ) {}
    }
}

private val testList = listOf(
    MessageUI(
        id = "0",
        authorId = "09",
        authorName = "Kevin",
        isMyMessage = false,
        timestamp = Timestamp(1, 0),
        text = "Hello, my dear friend I am so glad to hear you are doing great at work "
    ), MessageUI(
        id = "01",
        authorId = "091",
        authorName = "Olga",
        isMyMessage = true,
        timestamp = Timestamp(1, 0),
        text = "Hello, my dear friend"
    )
)
