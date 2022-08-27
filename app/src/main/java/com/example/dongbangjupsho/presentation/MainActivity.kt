package com.example.dongbangjupsho.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.dongbangjupsho.presentation.user.signup.UserSignUpViewModel
import com.example.dongbangjupsho.presentation.user.signin.UserSignInScreen
import com.example.dongbangjupsho.ui.theme.DongBangJupShoTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val userSignUpViewModel : UserSignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DongBangJupShoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    UserSignInScreen()
                    //KakaoSignInView(viewModel = kakaoAuthViewModel)
                }
            }
        }
    }
}
