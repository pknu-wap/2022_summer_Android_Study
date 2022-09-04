package com.example.dongbangjupsho.domain.use_case.firebase.auth

import com.example.dongbangjupsho.domain.model.AuthResult
import com.example.dongbangjupsho.domain.model.UserInfo
import com.example.dongbangjupsho.domain.repository.FirebaseAuthRepository
import javax.inject.Inject

class SignUp @Inject constructor(
    private val firebaseAuthRepository: FirebaseAuthRepository
) {
    suspend fun execute(userInfo : UserInfo): AuthResult{
        if(!firebaseAuthRepository.signUp(userInfo)) {
            return AuthResult(
                successful = false,
                errorMessage = "네트워크를 확인해주세요."
            )
        }
        return AuthResult(
            successful = true
        )
    }
}