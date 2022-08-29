package com.example.dongbangjupsho.presentation.user.signin

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dongbangjupsho.presentation.user.TextFieldState
import com.example.dongbangjupsho.domain.model.UserInfo
import com.example.dongbangjupsho.domain.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserSignInViewModel @Inject constructor(
    private val appContext: Application,
    private val firebaseRepository: FirebaseRepository
): ViewModel() {

    companion object{
        const val TAG = "KaKaoAuthViewModel"
    }

    private val _userId = mutableStateOf(TextFieldState(hint = "사용자 이름"))
    val userId : State<TextFieldState> = _userId

    private val _userPassword = mutableStateOf(TextFieldState(hint = "비밀번호"))
    val userPassword : State<TextFieldState> = _userPassword

    val signInState = MutableStateFlow<Boolean>(false)

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: UserSignInEvent){
        when(event){
            is UserSignInEvent.EnteredUserId ->{
                _userId.value = userId.value.copy(
                    text = event.value
                )
            }
            is UserSignInEvent.ChangeUserIdFocus ->{
                _userId.value = userId.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            userId.value.text.isBlank()
                )
            }

            is UserSignInEvent.EnteredPassword ->{
                _userPassword.value = userPassword.value.copy(
                    text = event.value
                )
            }
            is UserSignInEvent.ChangePasswordFocus ->{
                _userPassword.value = userPassword.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            userPassword.value.text.isBlank()
                )
            }
            is UserSignInEvent.SignInUser ->{
                signIn()
            }
        }
    }

    private fun signIn(){
        viewModelScope.launch{
            val result = firebaseRepository.signIn(
                UserInfo(
                userId.value.text,
                userPassword.value.text
                )
            )
            if(result){
                _eventFlow.emit(UiEvent.SignInUser)
            }else{
                _eventFlow.emit(UiEvent.ShowSnackbar("네트워크 연결을 확인해주세요."))
            }
        }
    }

    sealed class UiEvent{
        data class ShowSnackbar(val message: String): UiEvent()
        object SignInUser: UiEvent()
    }

}