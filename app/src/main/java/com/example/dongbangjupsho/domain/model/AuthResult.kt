package com.example.dongbangjupsho.domain.model

data class AuthResult (
    val successful : Boolean,
    val errorMessage: String? = null
        )