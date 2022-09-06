package com.example.dongbangjupsho.presentation.user.signin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dongbangjupsho.presentation.util.Screen
import kotlinx.coroutines.flow.collect

@Composable
fun UserSignInScreen(
    navController: NavController,
    viewModel: UserSignInViewModel = hiltViewModel()
){
    val scaffoldState = rememberScaffoldState()
    val state = viewModel.state

    LaunchedEffect(true){
        viewModel.signInEvents.collect { event ->
            when(event){
                is UserSignInViewModel.SignInEvent.Failure ->{
                    scaffoldState.snackbarHostState.showSnackbar(event.message.toString())
                }
                is UserSignInViewModel.SignInEvent.Success->{
                    navController.navigate(Screen.HomeScreen.route)
                }
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = state.email,
                onValueChange = {
                    viewModel.onEvent(UserSignInEvent.EmailChanged(it))
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "이메일")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = state.password,
                onValueChange = {
                    viewModel.onEvent(UserSignInEvent.PasswordChanged(it))
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "비밀번호")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { viewModel.onEvent(UserSignInEvent.Submit) }
                ) {
                    Text("로그인")
                }
                Spacer(modifier = Modifier.width(20.dp))
                Button(
                    onClick = { navController.navigate(Screen.UserSignUpScreen.route) }
                ) {
                    Text("회원가입")
                }
            }

        }
    }
}