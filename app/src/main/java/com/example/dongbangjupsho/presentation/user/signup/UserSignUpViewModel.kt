package com.example.dongbangjupsho.presentation.user.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dongbangjupsho.domain.model.UserInfo
import com.example.dongbangjupsho.domain.use_case.firebase.auth.SignUp
import com.example.dongbangjupsho.domain.use_case.validate.ValidateEmail
import com.example.dongbangjupsho.domain.use_case.validate.ValidateNickName
import com.example.dongbangjupsho.domain.use_case.validate.ValidatePassword
import com.example.dongbangjupsho.domain.use_case.validate.ValidateRepeatedPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserSignUpViewModel @Inject constructor(
    private val signUp: SignUp,
    private val validateEmail: ValidateEmail,
    private val validateNickName: ValidateNickName,
    private val validatePassword: ValidatePassword,
    private val validateRepeatedPassword: ValidateRepeatedPassword,
) : ViewModel(){

    var state by mutableStateOf(UserSignUpFormState())

    private val validationEventChannel = Channel<SignUpEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: UserSignUpEvent){
        when(event){
            is UserSignUpEvent.EmailChanged ->{
                state = state.copy(
                    email = event.email
                )
            }

            is UserSignUpEvent.PasswordChanged ->{
                state = state.copy(
                    password = event.password
                )
            }

            is UserSignUpEvent.RepeatedPasswordChanged ->{
                state = state.copy(
                    repeatedPassword = event.repeatedPassword
                )
            }

            is UserSignUpEvent.NickNameChanged ->{
                state = state.copy(
                    nickname = event.nickname
                )
            }

            is UserSignUpEvent.Submit -> {
                validateData()
            }
        }
    }
    private fun validateData(){
        val emailResult = validateEmail.execute(state.email)
        val nickNameResult = validateNickName.execute(state.nickname)
        val passwordResult = validatePassword.execute(state.password)
        val repeatedPasswordResult = validateRepeatedPassword.execute(state.password, state.repeatedPassword)

        val hasError = listOf(
            emailResult,
            nickNameResult,
            passwordResult,
            repeatedPasswordResult
        ).any{
            !it.successful
        }
        if(hasError){
            state = state.copy(
                emailError = emailResult.errorMessage,
                nicknameError = nickNameResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatedPasswordError = repeatedPasswordResult.errorMessage
            )
            return
        }
        submitData()
    }

    private fun submitData(){
        viewModelScope.launch {
            val signUpResult = signUp.execute(
                UserInfo(
                    state.email,
                    state.nickname,
                    state.password
                )
            )
            if(!signUpResult.successful){
                validationEventChannel.send(SignUpEvent.Failure(signUpResult.errorMessage))
            }else{
                validationEventChannel.send(SignUpEvent.Success)
            }
        }
    }

    sealed class SignUpEvent{
        data class Failure(val message: String?): SignUpEvent()
        object Success: SignUpEvent()
    }
}