package com.example.dongbangjupsho.data.repository
import com.example.dongbangjupsho.domain.repository.FirebaseRepository
import com.example.dongbangjupsho.domain.model.UserInfo
import com.example.dongbangjupsho.domain.util.FirebaseManager.firebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class FirebaseRepositoryImpl : FirebaseRepository{
    override suspend fun signUp(userInfo: UserInfo): Boolean =
        suspendCancellableCoroutine { cont ->
            firebaseAuth.createUserWithEmailAndPassword(userInfo.userId, userInfo.password)
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
}