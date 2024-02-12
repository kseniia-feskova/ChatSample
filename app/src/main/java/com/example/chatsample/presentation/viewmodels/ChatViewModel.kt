package com.example.chatsample.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatsample.domain.model.MessageUI
import com.example.chatsample.domain.usecase.chat.ICreateChatUseCase
import com.example.chatsample.domain.usecase.chat.IGetCompanionForChatUseCase
import com.example.chatsample.domain.usecase.chat.IGetListOfMessagesUseCase
import com.example.chatsample.domain.usecase.chat.ISendMessageUseCase
import com.example.chatsample.domain.usecase.chat.IUpdateUnreadChatUseCase
import com.example.chatsample.presentation.model.LoadListState
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChatViewModel @Inject constructor(
    private val getMessagesUseCase: IGetListOfMessagesUseCase,
    private val createChatUseCase: ICreateChatUseCase,
    private val sendMessageUseCase: ISendMessageUseCase,
    private val updateUnreadChatUseCase: IUpdateUnreadChatUseCase,
    private val getCompanionForChatUseCase: IGetCompanionForChatUseCase
) : ViewModel() {

    var messagesList: LoadListState<MessageUI> by mutableStateOf(LoadListState.Loading)
        private set

    private var chatId: String = ""
    private var companionId: String = ""

    fun setChatId(id: String) {
        chatId = id
        listenMessages()
        if (companionId.isEmpty()) viewModelScope.launch {
            companionId = getCompanionForChatUseCase.invoke(chatId)
        }
    }

    fun setCompanionId(id: String) {
        companionId = id
    }

    fun sendNewMessage(text: String) {
        viewModelScope.launch {
            if (chatId.isEmpty() && companionId.isNotEmpty()) {
                val newChatId = createChat()
                Log.e("sendNewMessage", "chatId = $chatId, newChatId = $newChatId")
                sendMessageUseCase.invoke(text, newChatId)
                updateUsersChats(newChatId)
            } else {
                Log.e("sendNewMessage", "chatId = $chatId")
                sendMessageUseCase.invoke(text, chatId)
                updateUsersChats(chatId)
            }
        }
    }

    fun updateChatAsRead() {
        viewModelScope.launch {
            updateUnreadChatUseCase(chatId = chatId, isRead = true)
        }
    }

    private suspend fun createChat(): String {
        val newChatId = createChatUseCase.invoke(companionId)
        setChatId(newChatId)
        return newChatId
    }

    private fun listenMessages() {
        viewModelScope.launch {
            if (chatId.isNotEmpty())
                getMessagesUseCase.invoke(chatId).collect {
                    messagesList = LoadListState.Success(it)
                }
            else messagesList = LoadListState.Success(emptyList())
        }
    }

    private suspend fun updateUsersChats(chatId: String) {
        if (companionId.isNotEmpty()) updateUnreadChatUseCase(companionId, chatId, false)
        updateUnreadChatUseCase(chatId = chatId, isRead = true)
    }
}