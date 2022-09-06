package com.example.dongbangjupsho.data.repository

import android.util.Log
import com.example.dongbangjupsho.domain.model.UserEnterInfo
import com.example.dongbangjupsho.domain.repository.FirebaseDatabaseRepository
import com.example.dongbangjupsho.domain.util.DB_KEY.Companion.HOME
import com.example.dongbangjupsho.domain.util.FirebaseManager
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import kotlinx.coroutines.suspendCancellableCoroutine
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.coroutines.resume

class FirebaseDatabaseRepositoryImpl : FirebaseDatabaseRepository {

    override suspend fun getTodayEnterPeople(timeStamp: String): String? =
        suspendCancellableCoroutine { cont ->
            FirebaseManager.firebaseDatabase.reference.child(HOME).child("TodayEnter").child(timeStamp)
                .addChildEventListener(object : ChildEventListener {
                    override fun onChildAdded(
                        snapshot: DataSnapshot,
                        previousChildName: String?
                    ) {
                        cont.resume(snapshot.childrenCount.toString())
                    }
                    override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?, ) {}
                    override fun onChildRemoved(snapshot: DataSnapshot) {}
                    override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
                    override fun onCancelled(error: DatabaseError) {
                        cont.resume(null)
                    }
                })
    }

    override suspend fun setTodayEnterPeople(userEnterInfo: UserEnterInfo): Boolean =
        suspendCancellableCoroutine { cont ->
            FirebaseManager.firebaseDatabase.reference.child(HOME).child("TodayEnter")
                .child(userEnterInfo.uid).child(userEnterInfo.nickName).setValue(userEnterInfo.nickName)
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
