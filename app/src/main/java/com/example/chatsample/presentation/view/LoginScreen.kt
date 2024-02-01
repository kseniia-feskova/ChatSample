package com.example.chatsample.presentation.view

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.material3.IconButton
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
import com.example.chatsample.presentation.model.UserUiState
import com.example.chatsample.presentation.navigation.Screen
import com.example.chatsample.presentation.ui.theme.Blue10
import com.example.chatsample.presentation.ui.theme.Blue30
import com.example.chatsample.presentation.ui.theme.Blue50
import com.example.chatsample.presentation.ui.theme.ChatSampleTheme
import com.example.chatsample.presentation.ui.theme.WhiteBlue
import com.example.chatsample.presentation.viewmodels.LoginViewModel

@Composable
fun LoginScreenContent(navController: NavController, viewModel: LoginViewModel) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Blue50),
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
                color = WhiteBlue
            )

            LoginForm(viewModel.userCheck, navController) { name, password ->
                viewModel.checkAndLogin(name, password)
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
                    text = "Back", color = WhiteBlue
                )
            }
        }
    }
}

@Composable
fun LoginForm(
    userCheck: UserUiState,
    navController: NavController?,
    sendData: (String, String) -> Unit
) {
    val context = LocalContext.current
    handleStatus(context, userCheck, navController)

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            shape = RoundedCornerShape(8.dp),
            placeholder = { Text("Имя пользователя") },
            colors = editTextColors()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            singleLine = true,
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                ShowPasswordIcon(passwordVisibility) {
                    passwordVisibility = !passwordVisibility
                }
            },
            placeholder = { Text("Пароль") },
            shape = RoundedCornerShape(8.dp),
            colors = editTextColors()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = WhiteBlue,
                disabledContentColor = Blue50,
                disabledContainerColor = Blue30
            ),
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .align(Alignment.CenterHorizontally),
            onClick = {
                onSecondButtonClick(
                    username,
                    password,
                    sendData
                )
            },
            enabled = username.isNotBlank() && password.isNotBlank()
        ) {
            Text(
                text = "Войти", color = Blue50
            )
        }
    }
}

@Composable
private fun editTextColors() = OutlinedTextFieldDefaults.colors(
    unfocusedTextColor = WhiteBlue,
    focusedTextColor = Blue50,
    unfocusedBorderColor = Blue50,
    focusedBorderColor = WhiteBlue,
    unfocusedContainerColor = Blue30,
    focusedContainerColor = WhiteBlue,
    unfocusedPlaceholderColor = Blue50,
    focusedPlaceholderColor = Blue10
)

@Composable
private fun ShowPasswordIcon(passwordVisibility: Boolean, onClick: (Boolean) -> Unit) {
    val image: ImageVector = if (passwordVisibility) Icons.Filled.Check else Icons.Filled.Close
    val description: String = if (passwordVisibility) "Скрыть пароль" else "Показать пароль"
    IconButton(
        onClick = { onClick(passwordVisibility) }) {
        Icon(imageVector = image, contentDescription = description)
    }
}

private fun handleStatus(
    context: Context,
    userUiState: UserUiState,
    navController: NavController?
) {
    when (userUiState) {
        is UserUiState.Loading -> Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
        is UserUiState.Success -> { navController?.navigate(Screen.CHATS.name) }
        is UserUiState.Error -> Toast.makeText(context, "Error occur ${userUiState.message}", Toast.LENGTH_SHORT).show()
        is UserUiState.Empty -> Log.e("RegistrationForm", "state is empty")
    }
}

private fun onSecondButtonClick(
    username: String,
    password: String,
    sendData: (String, String) -> Unit
) {
    sendData(username, password)
}

private fun onThirdButtonClick(navController: NavController?) {
    navController?.popBackStack()
}

@Preview(showBackground = true)
@Composable
fun LoginFormreview() {
    ChatSampleTheme {
        LoginForm(UserUiState.Empty, null) { name, paw ->
            Log.e("Registration form", "$name and $paw")
        }
    }
}
