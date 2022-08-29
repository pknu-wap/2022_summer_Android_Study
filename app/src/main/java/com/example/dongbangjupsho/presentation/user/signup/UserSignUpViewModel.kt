package com.example.dongbangjupsho.presentation.user.signup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dongbangjupsho.presentation.user.TextFieldState
import com.example.dongbangjupsho.domain.model.UserInfo
import com.example.dongbangjupsho.domain.repository.FirebaseAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserSignUpViewModel @Inject constructor(
    private val firebaseRepository: FirebaseAuthRepository
) : ViewModel(){

    private val _userId = mutableStateOf(TextFieldState(hint = "사용자 이름"))
    val userId : State<TextFieldState> = _userId

    private val _userPassword = mutableStateOf(TextFieldState(hint = "비밀번호"))
    val userPassword : State<TextFieldState> = _userPassword

    private val _userConfirmPassword = mutableStateOf(TextFieldState(hint = "비밀번호 확인"))
    val userConfirmPassword : State<TextFieldState> = _userConfirmPassword

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    fun onEvent(event: UserSignUpEvent){
        when(event){
            is UserSignUpEvent.EnteredUserId ->{
                _userId.value = userId.value.copy(
                    text = event.value
                )
            }
            is UserSignUpEvent.ChangeUserIdFocus ->{
                _userId.value = userId.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            userId.value.text.isBlank()
                )
            }

            is UserSignUpEvent.EnteredPassword ->{
                _userPassword.value = userPassword.value.copy(
                    text = event.value
                )
            }
            is UserSignUpEvent.ChangePasswordFocus ->{
                _userPassword.value = userPassword.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            userPassword.value.text.isBlank()
                )
            }
            is UserSignUpEvent.EnteredConfirmPassword ->{
                _userConfirmPassword.value = userConfirmPassword.value.copy(
                    text = event.value
                )
            }
            is UserSignUpEvent.ChangeConfirmPasswordFocus ->{
                _userConfirmPassword.value = userConfirmPassword.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            userConfirmPassword.value.text.isBlank()
                )
            }
            is UserSignUpEvent.SignUpUser -> {
                signUp()
            }
        }
    }
    private fun signUp(){
        viewModelScope.launch{
            viewModelScope.launch {
                if (_userPassword.value.text == _userConfirmPassword.value.text) {
                    try {
                        if(firebaseRepository.signUp(UserInfo(
                                userId = _userId.value.text,
                                password = _userPassword.value.text))
                        ) {
                            _eventFlow.emit(UiEvent.SignUpUser)
                        }else{
                            _eventFlow.emit(UiEvent.ShowSnackbar("네트워크 연결을 확인해주세요."))
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        _eventFlow.emit(UiEvent.ShowSnackbar("네트워크 연결을 확인해주세요."))
                    }
                } else {
                    _eventFlow.emit(UiEvent.ShowSnackbar("비밀번호가 일치하지 않습니다.."))
                }
            }
        }
    }
    sealed class UiEvent{
        data class ShowSnackbar(val message: String): UiEvent()
        object SignUpUser: UiEvent()
    }
}