package com.example.dongbangjupsho.data.repository
import com.example.dongbangjupsho.domain.repository.FirebaseAuthRepository
import com.example.dongbangjupsho.domain.model.UserInfo
import com.example.dongbangjupsho.domain.util.DB_KEY.Companion.USER
import com.example.dongbangjupsho.domain.util.FirebaseManager.firebaseAuth
import com.example.dongbangjupsho.domain.util.FirebaseManager.firebaseDatabase
import kotlinx.coroutines.*
import kotlin.coroutines.resume

class FirebaseAuthRepositoryImpl : FirebaseAuthRepository{
    override suspend fun signUp(userInfo: UserInfo): Boolean =
        suspendCancellableCoroutine { cont ->
            firebaseAuth.createUserWithEmailAndPassword(userInfo.userId, userInfo.password)
                .addOnSuccessListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        cont.resume(registerUser(userInfo))
                    }
                }
                .addOnFailureListener{
                    cont.resume(false)
                }
                .addOnCanceledListener {
                    cont.resume(false)
                }
        }

    override suspend fun signIn(userInfo: UserInfo): Boolean =
        suspendCancellableCoroutine { cont ->
            firebaseAuth.signInWithEmailAndPassword(userInfo.userId, userInfo.password)
                .addOnSuccessListener {
                    cont.resume(true)
                }
                .addOnFailureListener{
                    cont.resume(false)
                }
                .addOnCanceledListener {
                    cont.resume(false)
                }
        }

    private suspend fun registerUser(userInfo: UserInfo): Boolean =
        suspendCancellableCoroutine { cont ->
            firebaseDatabase.reference.child(USER).child(firebaseAuth.uid.toString())
                .child("userId").setValue(userInfo.userId)
                .addOnSuccessListener {
                    cont.resume(true)
                }
                .addOnFailureListener{
                    cont.resume(false)
                }
                .addOnCanceledListener {
                    cont.resume(false)
                }
        }
}