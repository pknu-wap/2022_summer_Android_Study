package com.example.dongbangjupsho.data.repository

import com.example.dongbangjupsho.domain.repository.FirebaseRepository
import com.example.dongbangjupsho.domain.model.UserInfo
import com.example.dongbangjupsho.domain.util.FirebaseManager

class FirebaseRepositoryImpl() : FirebaseRepository{
    override suspend fun signUp(userInfo: UserInfo): Boolean {
        return try{
            FirebaseManager.firebaseAuth.createUserWithEmailAndPassword(
                userInfo.userId, userInfo.password).isSuccessful
        }catch (e: Exception){
            e.printStackTrace()
            return false
        }
    }

    override suspend fun signIn(userInfo: UserInfo): Boolean {
        return try{
            FirebaseManager.firebaseAuth.signInWithEmailAndPassword(
                userInfo.userId, userInfo.password).isSuccessful

        }catch (e: Exception){
            e.printStackTrace()
            return false
        }
    }
}