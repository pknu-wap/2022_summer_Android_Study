package com.example.dongbangjupsho.presentation.user.signup

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dongbangjupsho.presentation.util.Screen
import kotlinx.coroutines.flow.collect


@Composable
fun UserSignUpScreen(
    navController: NavController,
    viewModel: UserSignUpViewModel = hiltViewModel()
){
    val state = viewModel.state
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(true){
        viewModel.validationEvents.collect { event ->
            when(event){
                is UserSignUpViewModel.SignUpEvent.Success ->{
                    navController.navigate(Screen.UserSignInScreen.route)
                }
                is UserSignUpViewModel.SignUpEvent.Failure ->{
                    scaffoldState.snackbarHostState.showSnackbar(event.message.toString())
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
            Text(
                text = "회원 가입",
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.height(20.dp))


            TextField(
                value = state.email,
                onValueChange = {
                    viewModel.onEvent(UserSignUpEvent.EmailChanged(it))
                },
                isError = state.emailError != null,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                              Text(text = "이메일")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )
            if(state.emailError != null){
                Text(
                    text = state.emailError,
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.align(Alignment.End)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = state.nickname,
                onValueChange = {
                    viewModel.onEvent(UserSignUpEvent.NickNameChanged(it))
                },
                isError = state.nicknameError != null,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "닉네임")
                }
            )
            if(state.nicknameError != null){
                Text(
                    text = state.nicknameError,
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.align(Alignment.End)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = state.password,
                onValueChange = {
                    viewModel.onEvent(UserSignUpEvent.PasswordChanged(it))
                },
                isError = state.passwordError != null,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "비밀번호")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation()
            )
            if(state.passwordError != null){
                Text(
                    text = state.passwordError,
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.align(Alignment.End)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = state.repeatedPassword,
                onValueChange = {
                    viewModel.onEvent(UserSignUpEvent.RepeatedPasswordChanged(it))
                },
                isError = state.repeatedPasswordError != null,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "비밀번호 확인")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation()
            )
            if(state.repeatedPasswordError != null){
                Text(
                    text = state.repeatedPasswordError,
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.align(Alignment.End)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.onEvent(UserSignUpEvent.Submit) }
            ) {
                Text("회원가입")
            }
        }
    }
}