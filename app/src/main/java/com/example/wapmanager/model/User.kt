package com.example.wapmanager.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(val profile: String? = null,val userId: String? = null, val userName: String? = null, val clubUpDate: Long) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}