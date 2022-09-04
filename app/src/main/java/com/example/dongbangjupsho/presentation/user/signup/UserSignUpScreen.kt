package com.example.dongbangjupsho.presentation.user.signup

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dongbangjupsho.presentation.user.component.HintTextField
import com.example.dongbangjupsho.presentation.util.Screen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun UserSignUpScreen(
    navController: NavController,
    viewModel: UserSignUpViewModel = hiltViewModel()
){
    val userIdState = viewModel.userId.value
    val passwordState = viewModel.userPassword.value
    val confirmPasswordState = viewModel.userConfirmPassword.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(true){
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is UserSignUpViewModel.UiEvent.ShowSnackbar ->{
                    scaffoldState.snackbarHostState.showSnackbar(event.message.toString())
                }
                is UserSignUpViewModel.UiEvent.SignUpUser ->{
                    navController.navigate(Screen.UserSignInScreen.route)
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
                .padding(16.dp),
        ) {
            Text(
                text = "회원 가입",
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.height(20.dp))


            HintTextField(
                text = userIdState.text,
                hint = userIdState.hint,
                onValueChange = {
                    viewModel.onEvent(UserSignUpEvent.EnteredUserId(it))
                },
                onFocusChange = {
                    viewModel.onEvent(UserSignUpEvent.ChangeUserIdFocus(it))
                },
                isHintVisible = userIdState.isHintVisible,
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(20.dp))

            HintTextField(
                text = passwordState.text,
                hint = passwordState.hint,
                onValueChange = {
                    viewModel.onEvent(UserSignUpEvent.EnteredPassword(it))
                },
                onFocusChange = {
                    viewModel.onEvent(UserSignUpEvent.ChangePasswordFocus(it))
                },
                isHintVisible = passwordState.isHintVisible,
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(20.dp))

            HintTextField(
                text = confirmPasswordState.text,
                hint = confirmPasswordState.hint,
                onValueChange = {
                    viewModel.onEvent(UserSignUpEvent.EnteredConfirmPassword(it))
                },
                onFocusChange = {
                    viewModel.onEvent(UserSignUpEvent.ChangeConfirmPasswordFocus(it))
                },
                isHintVisible = confirmPasswordState.isHintVisible,
                singleLine = true,
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { viewModel.onEvent(UserSignUpEvent.SignUpUser) }
            ) {
                Text("회원가입")
            }
        }
    }
}