package com.example.dongbangjupsho.presentation.home

sealed class UserEnterEvent{
    object SetUserEnter : UserEnterEvent()
    object LoadLocation : UserEnterEvent()
}
