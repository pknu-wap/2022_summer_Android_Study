package com.example.dongbangjupsho.presentation.util

sealed class Screen(val route: String) {
    object SignUpScreen : Screen("sign_up_screen")
    object SignInScreen : Screen("sign_in_screen")
    object HomeScreen : Screen("home_screen")
}