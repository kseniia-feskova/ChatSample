package com.example.chatsample.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domain.model.ChatUI
import com.domain.model.UserUI
import com.domain.usecase.chat.IGetAllChatsUseCase
import com.domain.usecase.chat.IGetCompanionsUseCase
import com.presentation.model.LoadListState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChatsViewModel @Inject constructor(
    private val getAllChatsUseCase: IGetAllChatsUseCase,
    private val getCompanionsUseCase: IGetCompanionsUseCase,
) : ViewModel() {
    var listOfChats: LoadListState<ChatUI> by mutableStateOf(LoadListState.Loading)
        private set
    var listOfCompanions: LoadListState<UserUI> by mutableStateOf(LoadListState.Loading)
        private set

    private var currentJob: Job? = null
    fun callAllChats() {
        currentJob = viewModelScope.launch {
            listOfChats = try {
                val chats = getAllChatsUseCase.invoke().sortedByDescending { it.timestamp }.sortedBy { it.isRead }
                LoadListState.Success(chats)
            } catch (e: Exception) {
                LoadListState.Error("Cannot callAllChats, ${e.message}")
            }
        }
    }

    fun callCompanions() {
        currentJob = viewModelScope.launch {
            listOfCompanions = try {
                val companions = getCompanionsUseCase.invoke()
                LoadListState.Success(companions)
            } catch (e: Exception) {
                LoadListState.Error("Cannot callCompanions, ${e.message}")
            }
        }
    }

    fun cancel() {
        currentJob?.cancel()
    }
}