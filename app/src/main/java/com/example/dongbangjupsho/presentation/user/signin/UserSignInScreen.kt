package com.example.dongbangjupsho.presentation.user.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dongbangjupsho.R
import com.example.dongbangjupsho.presentation.user.component.HintTextField
import com.example.dongbangjupsho.presentation.util.Screen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun UserSignInScreen(
    navController: NavController,
    viewModel: UserSignInViewModel = hiltViewModel()
){

    val userIdState = viewModel.userId.value
    val passwordState = viewModel.userPassword.value

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(true){
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is UserSignInViewModel.UiEvent.ShowSnackbar ->{
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
                is UserSignInViewModel.UiEvent.SignInUser ->{
                    navController.navigate(Screen.HomeScreen.route)
                }
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            HintTextField(
                text = userIdState.text,
                hint = userIdState.hint,
                onValueChange = {
                    viewModel.onEvent(UserSignInEvent.EnteredUserId(it))
                },
                onFocusChange = {
                    viewModel.onEvent(UserSignInEvent.ChangeUserIdFocus(it))
                },
                isHintVisible = userIdState.isHintVisible,
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(20.dp))

            HintTextField(
                text = passwordState.text,
                hint = passwordState.hint,
                onValueChange = {
                    viewModel.onEvent(UserSignInEvent.EnteredPassword(it))
                },
                onFocusChange = {
                    viewModel.onEvent(UserSignInEvent.ChangePasswordFocus(it))
                },
                isHintVisible = passwordState.isHintVisible,
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.onEvent(UserSignInEvent.SignInUser) }
            ) {
                Text("로그인")
            }

            Spacer(modifier = Modifier.height(16.dp))

            IconButton(
                onClick = {
                    viewModel.kakaoSignIn()
                    //todo 화면 전환 처리 navigation
                },
                modifier = Modifier
                    .height(40.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.kakao_login),
                    contentDescription = "Kakao_login",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit,
                )
            }

        }
    }
}