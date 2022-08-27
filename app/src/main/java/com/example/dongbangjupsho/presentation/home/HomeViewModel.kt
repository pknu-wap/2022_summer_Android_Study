package com.example.dongbangjupsho.presentation.home

import androidx.lifecycle.ViewModel
import com.example.dongbangjupsho.domain.repository.util.DB_KEY.Companion.HOME
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val databaseRef : DatabaseReference
): ViewModel(){

    init{

    }


    private suspend fun getCurrentNumber() : Boolean=
        suspendCoroutine { cont ->
            databaseRef.child(HOME).child("currentNumber").addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.childrenCount
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }
}