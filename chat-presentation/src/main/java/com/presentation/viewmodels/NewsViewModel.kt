package com.example.chatsample.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.domain.model.NewsUI
import com.domain.usecase.news.IGetNewsUseCase
import com.presentation.model.LoadListState
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val getNewsUseCase: IGetNewsUseCase
) : ViewModel() {

    var listOfNews: LoadListState<NewsUI> by mutableStateOf(LoadListState.Loading)
        private set

    fun callNews() {
        getNewsUseCase.invoke(
            onSuccess = { list ->
                listOfNews = LoadListState.Success(list)
            }, onError = { message ->
                listOfNews = LoadListState.Error(message)
            })
    }
}