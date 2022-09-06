package com.example.dongbangjupsho.domain.repository

import com.example.dongbangjupsho.domain.model.UserEnterInfo
import kotlinx.coroutines.flow.Flow

interface FirebaseDatabaseRepository {
    suspend fun getTodayEnterPeople(timeStamp: String): Flow<String?>
    suspend fun setTodayEnterPeople(userEnterInfo: UserEnterInfo): Boolean
}