package com.example.chatsample.presentation.view.items

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.chatsample.domain.model.NewsUI
import com.example.chatsample.presentation.view.ui.theme.ChatSampleTheme


@Composable
fun NewPreviewItem(new: NewsUI) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .clickable {
                openUrl(context, new.url)
            }
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(-2, -2, -1),
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Log.e("NewPreviewItem", "url = ${new.image}")

            Text(
                text = new.title,
                Modifier
                    .background(Color(10, 10, 100))
                    .padding(12.dp)
                    .align(Alignment.Start),
                maxLines = 2,
                style = MaterialTheme.typography.titleSmall,
                color = Color.White
            )
            Image(
                painter = rememberImagePainter(
                    data = new.image,
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop
            )
            Text(
                text = new.description,
                Modifier
                    .background(Color.White)
                    .padding(12.dp)
                    .align(Alignment.Start),
                maxLines = 3,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(10, 10, 100)
            )
        }
    }
}

fun openUrl(context: Context, source: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(source))
    context.startActivity(intent)
}

@Preview(showBackground = true)
@Composable
fun NewPreviewItemPreview() {
    ChatSampleTheme {
        NewPreviewItem(
            NewsUI(
                "0",
                "Kseniia",
                "Hello, my friend it's me",
                url = "",
                image = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fm.facebook.com%2FKotiki-105046794176321%2Fphotos%2F&psig=AOvVaw2MuSceNT3n-UbZcNidHpHm&ust=1707736125335000&source=images&cd=vfe&opi=89978449&ved=0CBMQjRxqFwoTCLj0xPqSo4QDFQAAAAAdAAAAABAE",
                author = "Alex"
            )
        )
    }
}