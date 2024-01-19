package com.example.chatsample.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.chatsample.ui.theme.ChatSampleTheme

@Composable
fun SecondScreenContent(navController: NavController? = null) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(12.dp)
        ) {
            val context = LocalContext.current
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp),
                text = "Second fragment",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(10, 10, 100)
            )

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(10, 10, 100)
                ),
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = { onSecondButtonClick(context) }
            ) {
                Text(
                    text = "Login", color = Color(-10, -10, -1)
                )
            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = { onThirdButtonClick(navController) }
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    text = "Back", color = Color(10, 10, 10)
                )
            }
        }
    }
}

fun onSecondButtonClick(context: Context) {
    Toast.makeText(context, "You are successfully loged in", Toast.LENGTH_LONG).show()
}

fun onThirdButtonClick(navController: NavController?) {
    navController?.popBackStack()
}

@Preview(showBackground = true)
@Composable
fun SecondScreenContentPreview() {
    ChatSampleTheme {
        SecondScreenContent()
    }
}
