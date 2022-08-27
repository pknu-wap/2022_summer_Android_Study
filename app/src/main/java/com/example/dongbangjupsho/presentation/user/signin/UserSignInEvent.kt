package com.example.dongbangjupsho.presentation.user.signin

import androidx.compose.ui.focus.FocusState

sealed class UserSignInEvent {

    data class EnteredUserId(val value: String) : UserSignInEvent()
    data class ChangeUserIdFocus(val focusState: FocusState): UserSignInEvent()
    data class EnteredPassword(val value: String) : UserSignInEvent()
    data class ChangePasswordFocus(val focusState: FocusState): UserSignInEvent()

    object SignInUser : UserSignInEvent()
}