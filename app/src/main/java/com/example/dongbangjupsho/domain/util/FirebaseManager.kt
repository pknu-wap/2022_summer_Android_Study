package com.example.dongbangjupsho.domain.util

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

object FirebaseManager {
    val firebaseAuth by lazy{ FirebaseAuth.getInstance()}
    val firebaseDatabase by lazy{ FirebaseDatabase.getInstance()}
}