package com.example.chatsample.presentation.view.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.chatsample.domain.model.NewsUI
import com.example.chatsample.presentation.model.LoadListState
import com.example.chatsample.presentation.view.items.NewPreviewItem
import com.example.chatsample.presentation.view.utils.BackButton
import com.example.chatsample.presentation.viewmodels.NewsViewModel

@Composable
fun NewsScreen(
    getVmFactory: () -> ViewModelProvider.Factory,
    viewModel: NewsViewModel = viewModel(factory = getVmFactory()),
    navController: NavController? = null
) {
    viewModel.callNews()
    NewsScreenContent(
        viewModel.listOfNews,
        navController,
    )
}

@Composable
fun NewsScreenContent(listOfChats: LoadListState<NewsUI>, navController: NavController? = null) {
    Column(modifier = Modifier.fillMaxSize()) {
        BackButton(
            navController = navController,
            modifier = Modifier.align(Alignment.Start)
        )
        when (listOfChats) {
            is LoadListState.Success -> {
                if (listOfChats.list.isEmpty()) {
                    EmptyNewsMessage()
                } else {
                    NewsList(listOfChats.list)
                }
            }

            is LoadListState.Loading -> {
                Log.e("RegistrationForm", "Loading")
            }

            is LoadListState.Error -> {
                ErrorMessage(listOfChats.message)
            }
        }
    }
}

@Composable
fun NewsList(list: List<NewsUI>) {
    Log.e("NewsList", "List = ${list.size}")
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(12.dp),
        ) {
            VerticalRecyclerViewNews(list)
        }
    }
}

@Composable
fun VerticalRecyclerViewNews(items: List<NewsUI>) {
    LazyColumn(contentPadding = PaddingValues(vertical = 8.dp)) {
        items(items = items) {
            NewPreviewItem(new = it)
        }
    }
}

@Preview
@Composable
fun NewsListPreview() {
    NewsScreenContent(LoadListState.Loading)
}

@Composable
fun EmptyNewsMessage() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .wrapContentSize(),
            textAlign = TextAlign.Center,
            text = "There is no news for now",
            style = MaterialTheme.typography.labelLarge,
            color = Color(10, 10, 100)
        )
    }
}

@Preview
@Composable
fun EmptyNewsMessagePreview() {
    EmptyNewsMessage()
}
