package com.example.chatsample.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatsample.domain.model.MessageUI
import com.example.chatsample.domain.usecase.chat.ICreateChatUseCase
import com.example.chatsample.domain.usecase.chat.IGetListOfMessagesUseCase
import com.example.chatsample.domain.usecase.chat.ISendMessageUseCase
import com.example.chatsample.presentation.model.LoadListState
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChatViewModel @Inject constructor(
    private val getMessagesUseCase: IGetListOfMessagesUseCase,
    private val createChatUseCase: ICreateChatUseCase,
    private val sendMessageUseCase: ISendMessageUseCase,
) : ViewModel() {

    //Output Мы выводим только список сообщений, который регулярно обновляется
    var messagesList: LoadListState<MessageUI> by mutableStateOf(LoadListState.Loading)
        private set

    // Input ChatId or CompanionId MessageText
    private var chatId: String = ""
    private var companionId: String = ""
    fun setChatId(id: String) {
        chatId = id
    }

    fun setCompanionId(id: String) {
        companionId = id
    }

    //getMessages - мы просто подписываемся на под коллекцию и всё показываем на экран
    fun listenMessages() {
        viewModelScope.launch {
            if (chatId.isNotEmpty())
                getMessagesUseCase.invoke(chatId).collect {
                    messagesList = LoadListState.Success(it)
                }
            else messagesList = LoadListState.Success(emptyList())
        }
    }

    fun sendNewMessage(text: String) {
        viewModelScope.launch {
            if (chatId.isEmpty() && companionId.isNotEmpty()) {
                createChat()
                sendMessageUseCase.invoke(text, chatId)
            } else {
                sendMessageUseCase.invoke(text, chatId)
            }
        }
    }

    private suspend fun createChat() {
        val newChatId = createChatUseCase.invoke(companionId)
        setUpCurrentChat(newChatId)
    }

    private fun setUpCurrentChat(newChatId: String) {
        setChatId(newChatId)
        listenMessages()
    }
    //setChatId - нам нужно поставить этот айди, чтобы потом подписаться на сообщения в этом чатике
    //setCompanionId - нам нужен собеседник. текущего юзера мы и так узнаем
    /* Два пути:
    * 1. На старте экрана, если мы перешли по чат превью
    * 2. При создании нового чата, когда отправили сообщение
    * */


    //createNewChatAndSendMessage{
    // createChat() - чтобы создать чат нам нужно получить айди, отправить список пользователей
    // setChatId () - создав чат, мы ставим его айди
    // getMessages () - подписываемся на сообщения
    // sendMessage() - создаем сообщение: получаем айди, пишем автора, добавляем текст, берем дату отправки и добавляем в коллецию
    // после добавления сообщения, мы обновляем таймстемп у самого документа чата и проходим по списку пользователей, чтобы обновить значение isRead
    // }

    //sendMessage
}