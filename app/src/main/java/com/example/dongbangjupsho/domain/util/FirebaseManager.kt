package com.example.dongbangjupsho.domain.util

import com.google.firebase.auth.FirebaseAuth

object FirebaseManager {
    val firebaseAuth by lazy{ FirebaseAuth.getInstance()}
}