package com.presentation.view.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.chat_presentation.R

@Composable
fun BackButton(navController: NavController?, modifier: Modifier) {
    Text(
        modifier = modifier
            .padding(16.dp)
            .clickable {
                onBackClick(navController)
            },
        text = stringResource(id = R.string.back), color = Color(10, 10, 100),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyMedium
    )
}

private fun onBackClick(navController: NavController?) {
    navController?.popBackStack()
}
