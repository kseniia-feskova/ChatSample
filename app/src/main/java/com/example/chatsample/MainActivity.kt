package com.example.chatsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.chatsample.ui.theme.ChatSampleTheme
import com.example.chatsample.view.BarkHomeContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatSampleTheme {
                MyApp()
            }
            // A surface container using the 'background' color from the theme
        }
    }
}

@Composable
fun MyApp() {
    Surface(
        content = {
            BarkHomeContent()
        }
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChatSampleTheme {
        Greeting("Android")
    }
}