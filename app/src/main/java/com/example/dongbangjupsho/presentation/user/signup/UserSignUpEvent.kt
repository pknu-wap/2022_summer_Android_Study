package com.example.dongbangjupsho.presentation.user.signup
sealed class UserSignUpEvent {

    data class EmailChanged(val email: String) : UserSignUpEvent()
    data class PasswordChanged(val password: String) : UserSignUpEvent()
    data class RepeatedPasswordChanged(val repeatedPassword: String) : UserSignUpEvent()
    data class NickNameChanged(val nickname : String): UserSignUpEvent()

    object Submit : UserSignUpEvent()
}