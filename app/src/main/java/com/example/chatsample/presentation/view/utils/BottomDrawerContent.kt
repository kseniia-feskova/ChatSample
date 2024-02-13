package com.example.chatsample.presentation.view.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chatsample.R
import com.example.chatsample.presentation.view.ui.theme.ChatSampleTheme

@Composable
fun <T> BottomDrawerContent(
    items: List<T>,
    modifier: Modifier,
    onClose: (DrawerValue) -> Unit,
    itemView: @Composable (T) -> Unit = {},
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(
            CornerSize(30.dp),
            CornerSize(30.dp),
            CornerSize(0.dp),
            CornerSize(0.dp)
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color(255, 255, 255, 255),
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(modifier = Modifier.background(Color.Transparent)) {
            IconButton(
                modifier = Modifier.align(Alignment.TopEnd),
                onClick = { onClose(DrawerValue.Open) },
                content = {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                        tint = Color(10, 10, 100)
                    )
                })

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = stringResource(id = R.string.select_companion),
                    style = MaterialTheme.typography.labelLarge,
                    color = Color(10, 10, 100)
                )
                LazyColumn {
                    items(items) { contact ->
                        itemView(contact)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun BottomDrawerContentPreview() {
    ChatSampleTheme {
        BottomDrawerContent(modifier = Modifier
            .wrapContentHeight(),
            items = listOf("Selectial", "Cortney", "Fibie", "Ross"),
            onClose = { },
            itemView = {
                Text(
                    text = "- $it",
                    modifier = Modifier.padding(8.dp),
                    color = Color(10, 10, 100),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        )
    }
}
