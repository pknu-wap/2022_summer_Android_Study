package com.example.dongbangjupsho.presentation.user.signup

data class UserSignUpFormState (
    val email: String = "",
    val emailError: String? ="",
    val password: String = "",
    val passwordError: String? = "",
    val repeatedPassword : String = "",
    val repeatedPasswordError: String? = "",
    val nickname : String = "",
    val nicknameError: String? = ""
    )