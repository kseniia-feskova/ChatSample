package com.example.chatsample.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatsample.domain.usecase.chat.IGetAllChatsUseCase
import com.example.chatsample.domain.usecase.chat.IGetCompanionsUseCase
import com.example.chatsample.presentation.model.ChatsListState
import com.example.chatsample.presentation.model.UsersListState
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChatsViewModel @Inject constructor(
    private val getAllChatsUseCase: IGetAllChatsUseCase,
    private val getCompanionsUseCase: IGetCompanionsUseCase
) : ViewModel() {
    var listOfChats: ChatsListState by mutableStateOf(ChatsListState.Loading)
        private set
    var listOfCompanions: UsersListState by mutableStateOf(UsersListState.Loading)
        private set

    fun callAllChats() {
        viewModelScope.launch {
            listOfChats = try {
                val chats = getAllChatsUseCase.invoke()
                Log.e("ChatsViewModel", "chats = $chats")
                ChatsListState.Success(chats)
            } catch (e: Exception) {
                ChatsListState.Error("Cannot callAllChats, ${e.message}")
            }
        }
    }

    fun callCompanions() {
        viewModelScope.launch {
            listOfCompanions = try {
                val companions = getCompanionsUseCase.invoke()
                Log.e("ChatsViewModel", "companions = $companions")
                UsersListState.Success(companions)
            } catch (e: Exception) {
                UsersListState.Error("Cannot callCompanions, ${e.message}")
            }
        }
    }
}