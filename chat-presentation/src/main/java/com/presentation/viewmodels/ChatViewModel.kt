package com.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domain.model.MessageUI
import com.domain.usecase.chat.ICreateChatUseCase
import com.domain.usecase.chat.IDeleteChatUseCase
import com.domain.usecase.chat.IDeleteMessageUseCase
import com.domain.usecase.chat.IGetCompanionForChatUseCase
import com.domain.usecase.chat.IGetListOfMessagesUseCase
import com.domain.usecase.chat.ISendMessageUseCase
import com.domain.usecase.chat.IUpdateUnreadChatUseCase
import com.presentation.model.LoadListState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChatViewModel @Inject constructor(
    private val getMessagesUseCase: IGetListOfMessagesUseCase,
    private val createChatUseCase: ICreateChatUseCase,
    private val sendMessageUseCase: ISendMessageUseCase,
    private val updateUnreadChatUseCase: IUpdateUnreadChatUseCase,
    private val getCompanionForChatUseCase: IGetCompanionForChatUseCase,
    private val deleteChatUseCase: IDeleteChatUseCase,
    private val deleteMessageUseCase: IDeleteMessageUseCase
) : ViewModel() {

    var messagesList: LoadListState<MessageUI> by mutableStateOf(LoadListState.Loading)
        private set

    private var chatId: String = ""
    private var companionId: String = ""
    private var currentJob: Job? = null

    fun setChatId(id: String) {
        chatId = id
        listenMessages()
        if (companionId.isEmpty()) {
            currentJob = viewModelScope.launch {
                try {
                    companionId = getCompanionForChatUseCase.invoke(chatId)
                } catch (e: Exception) {
                    Log.e("setChatId", "Error = ${e.message}")
                }
            }
        }
    }

    fun setCompanionId(id: String) {
        companionId = id
    }

    fun sendNewMessage(text: String) {
        viewModelScope.launch {
            try {
                if (chatId.isEmpty() && companionId.isNotEmpty()) {
                    val newChatId = createChat()
                    sendMessageUseCase.invoke(text, newChatId)
                    updateUsersChats(newChatId)
                } else {
                    sendMessageUseCase.invoke(text, chatId)
                    updateUsersChats(chatId)
                }
            } catch (e: Exception) {
                Log.e("sendNewMessage", "Error = ${e.message}")
            }
        }
    }

    fun updateChatAsRead() {
        viewModelScope.launch {
            try {
                updateUnreadChatUseCase(chatId = chatId, isRead = true)
            } catch (e: Exception) {
                Log.e("updateChatAsRead", "Error = ${e.message}")
            }
        }
    }

    fun deleteChat() {
        if (chatId.isNotEmpty()) {
            viewModelScope.launch {
                try {
                    deleteChatUseCase.invoke(chatId)
                } catch (e: Exception) {
                    Log.e("deleteChat", "Error = ${e.message}")
                }
            }
        }
    }

    fun deleteMessage(messageUI: MessageUI) {
        if (chatId.isNotEmpty()) {
            viewModelScope.launch {
                try {
                    deleteMessageUseCase(messageUI, chatId)
                } catch (e: Exception) {
                    Log.e("deleteMessage", "Error = ${e.message}")
                }
            }
        }
    }

    fun cancel() {
        currentJob?.cancel()
    }

    private suspend fun createChat(): String {
        val newChatId = createChatUseCase.invoke(companionId)
        setChatId(newChatId)
        return newChatId
    }

    private fun listenMessages() {
        currentJob = viewModelScope.launch {
            try {
                if (chatId.isNotEmpty())
                    getMessagesUseCase.invoke(chatId).collect {
                        messagesList = LoadListState.Success(it)
                    }
                else messagesList = LoadListState.Success(emptyList())
            } catch (e: Exception) {
                LoadListState.Error("Cannot get messages, ${e.message}")
            }
        }
    }

    private suspend fun updateUsersChats(chatId: String) {
        if (companionId.isNotEmpty()) updateUnreadChatUseCase(companionId, chatId, false)
        updateUnreadChatUseCase(chatId = chatId, isRead = true)
    }
}