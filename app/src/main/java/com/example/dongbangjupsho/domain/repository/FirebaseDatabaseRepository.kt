package com.example.dongbangjupsho.domain.repository

import com.example.dongbangjupsho.domain.model.UserEnterInfo

interface FirebaseDatabaseRepository {
    suspend fun getTodayEnterPeople(timeStamp: String): String?
    suspend fun setTodayEnterPeople(userEnterInfo: UserEnterInfo): Boolean
}