package com.example.chatsample.presentation.view.screens

import android.content.Context
import android.util.Log
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
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.chat_presentation.R
import com.presentation.model.UserUiState
import com.presentation.navigation.Screen
import com.example.chatsample.presentation.view.ui.theme.Blue10
import com.example.chatsample.presentation.view.ui.theme.Blue30
import com.example.chatsample.presentation.view.ui.theme.Blue50
import com.example.chatsample.presentation.view.ui.theme.ChatSampleTheme
import com.example.chatsample.presentation.view.ui.theme.WhiteBlue
import com.presentation.view.utils.BackButton
import com.example.chatsample.presentation.viewmodels.SignupViewModel

@Composable
fun SignUpScreenContent(
    navController: NavController? = null,
    getVmFactory: () -> ViewModelProvider.Factory,
    viewModel: SignupViewModel = viewModel(factory = getVmFactory()),
) {
    Log.e("SignUpScreenContent", "Draw SignUpScreenContent")
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(12.dp)
        ) {
            RegistrationForm(
                viewModel.userCreation,
                navController = navController
            ) { name, password ->
                viewModel.checkAndCreateUser(name, password)
            }

            BackButton(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .align(Alignment.CenterHorizontally),
                navController = navController
            )
        }
    }
}

@Composable
fun RegistrationForm(
    userUiState: UserUiState,
    navController: NavController?,
    sendData: (String, String) -> Unit,
) {
    val context = LocalContext.current
    HandleStatus(context, userUiState, navController)

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
            placeholder = { Text(stringResource(id = R.string.username)) },
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
            placeholder = { Text(stringResource(id = R.string.password)) },
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
            trailingIcon = {
                ShowPasswordIcon(confirmPasswordVisibility) {
                    confirmPasswordVisibility = !confirmPasswordVisibility
                }
            },
            placeholder = { Text(stringResource(id = R.string.confirm_password)) },
            shape = RoundedCornerShape(8.dp),
            colors = editTextColors()
        )

        Spacer(modifier = Modifier.height(16.dp))
        val error = stringResource(id = R.string.error_different_passwords)
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
                onSignUpClick(
                    error,
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
                text = stringResource(id = R.string.signup), color = Blue50
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
    val image = if (passwordVisibility) Icons.Filled.Check else Icons.Filled.Close
    val description = if (passwordVisibility) stringResource(id = R.string.hide_password)
    else stringResource(id = R.string.show_password)
    IconButton(
        onClick = { onClick(passwordVisibility) }) {
        Icon(imageVector = image, contentDescription = description)
    }
}

@Composable
private fun HandleStatus(
    context: Context,
    userUiState: UserUiState,
    navController: NavController?
) {
    when (userUiState) {
        is UserUiState.Loading -> Log.e("RegistrationForm", "Loading")
        is UserUiState.Success -> {
            navController?.navigate(Screen.MAIN.name)
        }

        is UserUiState.Error -> Toast.makeText(
            context,
            stringResource(id = R.string.error_occur, userUiState.message),
            Toast.LENGTH_SHORT
        ).show()

        is UserUiState.Empty -> Log.e("RegistrationForm", "state is empty")
    }
}

private fun onSignUpClick(
    error: String,
    context: Context,
    username: String,
    password: String,
    confirmPassword: String,
    sendData: (String, String) -> Unit
) {
    if (password != confirmPassword) {
        Toast.makeText(
            context,
            error,
            Toast.LENGTH_SHORT
        ).show()
    } else {
        sendData(username, password)
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationFormPreview() {
    ChatSampleTheme {
        RegistrationForm(UserUiState.Empty, null) { name, paw ->
            Log.e("Registration form", "$name and $paw")
        }
    }
}
