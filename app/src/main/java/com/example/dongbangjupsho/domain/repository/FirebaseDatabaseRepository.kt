package com.example.dongbangjupsho.domain.repository

interface FirebaseDatabaseRepository {

    suspend fun getTodayEnterPeople(): String?
    suspend fun setTodayEnterPeople(uid: String): Boolean
}