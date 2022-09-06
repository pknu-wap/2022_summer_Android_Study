package com.example.dongbangjupsho.data.repository

import android.util.Log
import com.example.dongbangjupsho.domain.model.UserEnterInfo
import com.example.dongbangjupsho.domain.repository.FirebaseDatabaseRepository
import com.example.dongbangjupsho.domain.util.DB_KEY.Companion.HOME
import com.example.dongbangjupsho.domain.util.FirebaseManager
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.coroutines.resume

@ExperimentalCoroutinesApi
class FirebaseDatabaseRepositoryImpl : FirebaseDatabaseRepository {

    override suspend fun getTodayEnterPeople(timeStamp: String): Flow<String?> =
        callbackFlow {
            FirebaseManager.firebaseDatabase.reference.child("TodayEnter")
                .child(timeStamp)
                .addChildEventListener(object : ChildEventListener {
                    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                        trySend(snapshot.childrenCount.toString())
                    }
                    override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                        trySend(snapshot.childrenCount.toString())
                    }
                    override fun onChildRemoved(snapshot: DataSnapshot) {}
                    override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
                    override fun onCancelled(error: DatabaseError) {
                        trySend(null)
                    }
                })
            awaitClose()
        }


    override suspend fun setTodayEnterPeople(userEnterInfo: UserEnterInfo): Boolean =
        suspendCancellableCoroutine { cont ->
            FirebaseManager.firebaseDatabase.reference.child("TodayEnter").child(userEnterInfo.timeStamp)
                .child("userId").child(userEnterInfo.uid).setValue(userEnterInfo.nickName)
                .addOnSuccessListener {
                    cont.resume(true)
                }
                .addOnFailureListener{
                    cont.resume(false)
                }
                .addOnCanceledListener{
                    cont.resume(false)
                }
        }
}
