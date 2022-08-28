package com.example.dongbangjupsho.domain.repository

import com.example.dongbangjupsho.domain.model.UserInfo

interface FirebaseRepository {
    suspend fun signUp(userInfo: UserInfo): Boolean
    suspend fun signIn(userInfo: UserInfo): Boolean
}