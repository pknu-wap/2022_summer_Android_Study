package com.example.dongbangjupsho.presentation.user.signin

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dongbangjupsho.presentation.user.signup.UserSignUpFormState
import com.example.dongbangjupsho.domain.model.UserInfo
import com.example.dongbangjupsho.domain.use_case.firebase.auth.SignIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserSignInViewModel @Inject constructor(
    private val signIn: SignIn
): ViewModel() {

    var state by mutableStateOf(UserSignInForm())

    private val signInEventChannel = Channel<SignInEvent>()
    val signInEvents  = signInEventChannel.receiveAsFlow()

    fun onEvent(event: UserSignInEvent){
        when(event){
            is UserSignInEvent.EmailChanged ->{
                state = state.copy(
                    email = event.email
                )
            }
            is UserSignInEvent.PasswordChanged ->{
                state = state.copy(
                    password = event.password
                )
            }

            is UserSignInEvent.Submit ->{
                submitData()
            }
        }
    }

    private fun submitData(){
        viewModelScope.launch{
            val result = signIn.execute(
                UserInfo(
                    state.email,
                    state.password
                )
            )
            if(result.successful){
                signInEventChannel.send(SignInEvent.Success)
            }else{
                signInEventChannel.send(SignInEvent.Failure(result.errorMessage))
            }
        }
    }

    sealed class SignInEvent{
        data class Failure(val message: String?): SignInEvent()
        object Success: SignInEvent()
    }

}