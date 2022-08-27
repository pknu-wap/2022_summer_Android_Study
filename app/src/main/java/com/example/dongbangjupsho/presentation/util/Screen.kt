package com.example.dongbangjupsho.presentation.util

sealed class Screen(val route: String) {
    object UserSignUpScreen : Screen("user_sign_up_screen")
    object UserSignInScreen : Screen("user_sign_in_screen")
    object HomeScreen : Screen("home_screen")
}