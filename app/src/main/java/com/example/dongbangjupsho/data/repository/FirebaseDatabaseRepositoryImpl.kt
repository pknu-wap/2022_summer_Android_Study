package com.example.dongbangjupsho.data.repository

import com.example.dongbangjupsho.domain.repository.FirebaseDatabaseRepository
import com.example.dongbangjupsho.domain.util.DB_KEY.Companion.HOME
import com.example.dongbangjupsho.domain.util.FirebaseManager
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class FirebaseDatabaseRepositoryImpl : FirebaseDatabaseRepository {

    override suspend fun getTodayEnterPeople(): String? =
        suspendCancellableCoroutine { cont ->
            FirebaseManager.firebaseDatabase.reference.child(HOME).child("TodayEnter")
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

}
