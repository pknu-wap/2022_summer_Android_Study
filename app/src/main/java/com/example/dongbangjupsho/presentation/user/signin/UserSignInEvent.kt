package com.example.dongbangjupsho.presentation.user.signin

sealed class UserSignInEvent {

    data class EmailChanged(val email: String) : UserSignInEvent()
    data class PasswordChanged(val password: String) : UserSignInEvent()

    object Submit : UserSignInEvent()
}