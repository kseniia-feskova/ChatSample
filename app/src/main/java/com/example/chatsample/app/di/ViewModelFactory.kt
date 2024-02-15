package com.example.chatsample.app.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.domain.usecase.IIsUserLoggedInUseCase
import com.domain.usecase.chat.ICreateChatUseCase
import com.domain.usecase.chat.IDeleteChatUseCase
import com.domain.usecase.chat.IDeleteMessageUseCase
import com.domain.usecase.chat.IGetAllChatsUseCase
import com.domain.usecase.chat.IGetCompanionForChatUseCase
import com.domain.usecase.chat.IGetCompanionsUseCase
import com.domain.usecase.chat.IGetListOfMessagesUseCase
import com.domain.usecase.chat.ISendMessageUseCase
import com.domain.usecase.chat.IUpdateUnreadChatUseCase
import com.domain.usecase.login.ICheckUsernameUseCase
import com.domain.usecase.login.ICreateUserUseCase
import com.domain.usecase.login.ILogOutUseCase
import com.domain.usecase.login.ILoginUseCase
import com.domain.usecase.news.IGetNewsUseCase
import com.example.chatsample.presentation.viewmodels.ChatViewModel
import com.example.chatsample.presentation.viewmodels.ChatsViewModel
import com.example.chatsample.presentation.viewmodels.HomeViewModel
import com.example.chatsample.presentation.viewmodels.LoginViewModel
import com.example.chatsample.presentation.viewmodels.MainViewModel
import com.example.chatsample.presentation.viewmodels.NewsViewModel
import com.example.chatsample.presentation.viewmodels.SignupViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(
    private val checkUserName: ICheckUsernameUseCase,
    private val createUser: ICreateUserUseCase,
    private val login: ILoginUseCase,
    private val isLoggedIn: IIsUserLoggedInUseCase,
    private val getAllChatsUseCase: IGetAllChatsUseCase,
    private val getCompanionsUseCase: IGetCompanionsUseCase,
    private val getMessagesUseCase: IGetListOfMessagesUseCase,
    private val createChatUseCase: ICreateChatUseCase,
    private val sendMessageUseCase: ISendMessageUseCase,
    private val updateUnreadChatUseCase: IUpdateUnreadChatUseCase,
    private val getCompanionForChatUseCase: IGetCompanionForChatUseCase,
    private val logOutUseCase: ILogOutUseCase,
    private val getNewsUseCase: IGetNewsUseCase,
    private val deleteChatUseCase: IDeleteChatUseCase,
    private val deleteMessageUseCase: IDeleteMessageUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            return SignupViewModel(checkUserName, createUser) as T
        }
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(login) as T
        }
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(isLoggedIn) as T
        }
        if (modelClass.isAssignableFrom(ChatsViewModel::class.java)) {
            return ChatsViewModel(
                getAllChatsUseCase,
                getCompanionsUseCase
            ) as T
        }
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            return ChatViewModel(
                getMessagesUseCase,
                createChatUseCase,
                sendMessageUseCase,
                updateUnreadChatUseCase,
                getCompanionForChatUseCase,
                deleteChatUseCase,
                deleteMessageUseCase
            ) as T
        }
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(getNewsUseCase) as T
        }
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(logOutUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}