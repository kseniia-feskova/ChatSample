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
import com.example.chatsample.presentation.ui.theme.Blue10
import com.example.chatsample.presentation.ui.theme.Blue30
import com.example.chatsample.presentation.ui.theme.Blue50
import com.example.chatsample.presentation.ui.theme.ChatSampleTheme
import com.example.chatsample.presentation.ui.theme.WhiteBlue
import com.example.chatsample.presentation.viewmodels.SignupViewModel

@Composable
fun SignUpScreenContent(navController: NavController, viewModel: SignupViewModel) {
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

            RegistrationForm(viewModel.userCreation) { name, password ->
                viewModel.checkAndCreateUser(name, password)
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
fun RegistrationForm(
    userUiState: UserUiState,
    sendData: (String, String) -> Unit
) {
    val context = LocalContext.current
    handleStatus(context, userUiState)

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
            trailingIcon = { ShowPasswordIcon(passwordVisibility) { passwordVisibility = !passwordVisibility } },
            placeholder = { Text("Пароль") },
            shape = RoundedCornerShape(8.dp),
            colors = editTextColors()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            singleLine = true,
            visualTransformation = if (confirmPasswordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = { ShowPasswordIcon(confirmPasswordVisibility) { confirmPasswordVisibility = !confirmPasswordVisibility } },
            placeholder = { Text("Подтвердите пароль") },
            shape = RoundedCornerShape(8.dp),
            colors = editTextColors()
        )

        Spacer(modifier = Modifier.height(16.dp))

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
                    context,
                    username,
                    password,
                    confirmPassword,
                    sendData
                )
            },
            enabled = username.isNotBlank() && password.isNotBlank() && confirmPassword.isNotBlank()
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

private fun handleStatus(context: Context, userUiState: UserUiState) {
    when (userUiState) {
        is UserUiState.Loading -> Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
        is UserUiState.Success -> Toast.makeText(context, "You are successfully loged in", Toast.LENGTH_SHORT).show()
        is UserUiState.Error -> Toast.makeText(context, "Error occur ${userUiState.message}", Toast.LENGTH_SHORT).show()
        is UserUiState.Empty -> Log.e("RegistrationForm", "state is empty")
    }
}

private fun onSecondButtonClick(
    context: Context,
    username: String,
    password: String,
    confirmPassword: String,
    sendData: (String, String) -> Unit
) {
    if (password != confirmPassword) {
        Toast.makeText(context, "Passwords must be the same", Toast.LENGTH_SHORT).show()
    } else {
        sendData(username, password)
    }
}

private fun onThirdButtonClick(navController: NavController?) {
    navController?.popBackStack()
}

@Preview(showBackground = true)
@Composable
fun RegistrationFormPreview() {
    ChatSampleTheme {
        RegistrationForm(UserUiState.Empty) { name, paw ->
            Log.e("Registreion form", "$name and $paw")
        }
    }
}
