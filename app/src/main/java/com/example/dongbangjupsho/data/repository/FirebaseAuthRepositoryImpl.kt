package com.example.dongbangjupsho.data.repository
import android.content.SharedPreferences
import com.example.dongbangjupsho.domain.repository.FirebaseAuthRepository
import com.example.dongbangjupsho.domain.model.UserInfo
import com.example.dongbangjupsho.domain.util.DB_KEY.Companion.USER
import com.example.dongbangjupsho.domain.util.FirebaseManager.firebaseAuth
import com.example.dongbangjupsho.domain.util.FirebaseManager.firebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.*
import kotlin.coroutines.resume

class FirebaseAuthRepositoryImpl(
    private val prefs : SharedPreferences
) : FirebaseAuthRepository{
    override suspend fun signUp(userInfo: UserInfo): Boolean =
        suspendCancellableCoroutine { cont ->
            firebaseAuth.createUserWithEmailAndPassword(userInfo.email, userInfo.password)
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

    override suspend fun signIn(email:String, password: String): Boolean =
        suspendCancellableCoroutine { cont ->
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    it.user?.let{
                        CoroutineScope(Dispatchers.IO).launch {
                            val nickName = getUserNickName(it.uid)
                            if (nickName != null) {
                                prefs.edit()
                                    .putString("nickName", nickName)
                                    .apply()
                                cont.resume(true)
                            } else {
                                cont.resume(false)
                            }
                        }
                    }
                }
                .addOnFailureListener{
                    cont.resume(false)
                }
                .addOnCanceledListener {
                    cont.resume(false)
                }
        }
    //todo 날짜에 맞게
    private suspend fun registerUser(userInfo: UserInfo): Boolean =
        suspendCancellableCoroutine { cont ->
            firebaseDatabase.reference.child(USER).child(firebaseAuth.uid.toString())
                .child("userInfo").setValue(userInfo.copy(password = ""))
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
    private suspend fun getUserNickName(uid: String): String? =
        suspendCancellableCoroutine { cont ->
            firebaseDatabase.reference.child(USER).child(uid).child("userInfo").child("nickName")
                .addValueEventListener(object: ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        cont.resume(snapshot.value.toString())
                    }

                    override fun onCancelled(error: DatabaseError) {
                        cont.resume(null)
                    }
                })
        }
}