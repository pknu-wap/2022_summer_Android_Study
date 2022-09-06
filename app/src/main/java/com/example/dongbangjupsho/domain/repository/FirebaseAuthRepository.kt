package com.example.dongbangjupsho.domain.repository

import com.example.dongbangjupsho.domain.model.UserInfo

interface FirebaseAuthRepository {
    suspend fun signUp(userInfo: UserInfo): Boolean
    suspend fun signIn(email: String, password: String): Boolean
}