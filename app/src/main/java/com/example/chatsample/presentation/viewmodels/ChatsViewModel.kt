package com.example.chatsample.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatsample.domain.usecase.chat.IGetAllChatsUseCase
import com.example.chatsample.presentation.model.ChatsListState
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChatsViewModel @Inject constructor(
    private val getAllChatsUseCase: IGetAllChatsUseCase
) : ViewModel() {
    var listOfChats: ChatsListState by mutableStateOf(ChatsListState.Loading)
        private set

    fun callAllChats() {
        viewModelScope.launch {
            val chats = getAllChatsUseCase.invoke()
            Log.e("ChatsViewModel", "chats = $chats")
            listOfChats = ChatsListState.Success(chats)
        }
    }
}