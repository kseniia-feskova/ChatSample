package com.example.chatsample.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.chatsample.ui.theme.ChatSampleTheme
import androidx.compose.material3.IconButton as IconButton

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
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp),
                text = "Second fragment",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(10, 10, 100)
            )

            RegistrationForm()

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

@Composable
fun RegistrationForm() {
    val context = LocalContext.current

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var confirmPasswordVisibility by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Имя пользователя") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            singleLine = true,
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image: ImageVector =
                    if (passwordVisibility) Icons.Filled.Check else Icons.Filled.Close
                val description: String =
                    if (passwordVisibility) "Скрыть пароль" else "Показать пароль"
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(imageVector = image, contentDescription = description)
                }
            },
            label = { Text("Пароль") },
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(10, 10, 100),
                unfocusedContainerColor = Color.White,
                focusedLabelColor = Color(10, 10, 100),
                unfocusedLabelColor = Color(10, 10, 100)
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            singleLine = true,
            visualTransformation = if (confirmPasswordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image: ImageVector =
                    if (confirmPasswordVisibility) Icons.Filled.Check else Icons.Filled.Close
                val description: String =
                    if (confirmPasswordVisibility) "Скрыть пароль" else "Показать пароль"
                IconButton(onClick = { confirmPasswordVisibility = !confirmPasswordVisibility }) {
                    Icon(imageVector = image, contentDescription = description)
                }
            },
            label = { Text("Подтвердите пароль") },
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(10, 10, 100),
                unfocusedContainerColor = Color.White,
                focusedLabelColor = Color(10, 10, 100),
                unfocusedLabelColor = Color(10, 10, 100)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(10, 10, 100)
            ),
            modifier = Modifier
                .padding(vertical = 8.dp)
                .align(Alignment.CenterHorizontally),
            onClick = { onSecondButtonClick(context, username, password, confirmPassword) },
            enabled = username.isNotBlank() && password.isNotBlank() && confirmPassword.isNotBlank()
        ) {
            Text(
                text = "Login", color = Color(-10, -10, -1)
            )
        }
    }
}


fun onSecondButtonClick(
    context: Context,
    username: String,
    password: String,
    confirmPassword: String
) {
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

@Preview(showBackground = true)
@Composable
fun RegistrationFormPreview() {
    ChatSampleTheme {
        RegistrationForm()
    }
}
