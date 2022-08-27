package com.example.dongbangjupsho.presentation.user.signup

import androidx.compose.ui.focus.FocusState

sealed class UserSignUpEvent {

    data class EnteredUserId(val value: String) : UserSignUpEvent()
    data class ChangeUserIdFocus(val focusState: FocusState): UserSignUpEvent()
    data class EnteredPassword(val value: String) : UserSignUpEvent()
    data class ChangePasswordFocus(val focusState: FocusState): UserSignUpEvent()
    data class EnteredConfirmPassword(val value: String) : UserSignUpEvent()
    data class ChangeConfirmPasswordFocus(val focusState: FocusState): UserSignUpEvent()

    object SignUpUser : UserSignUpEvent()
}