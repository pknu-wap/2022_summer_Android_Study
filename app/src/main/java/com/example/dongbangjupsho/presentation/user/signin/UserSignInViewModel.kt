package com.example.dongbangjupsho.presentation.user.signin

import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dongbangjupsho.presentation.user.TextFieldState
import com.example.dongbangjupsho.domain.model.UserInfo
import com.example.dongbangjupsho.domain.repository.FirebaseRepository
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

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
                viewModelScope.launch {
                    try {
                        if (firebaseRepository.signIn(UserInfo(
                                userId = userId.value.text,
                                password = userPassword.value.text
                            ))) {
                            _eventFlow.emit(UiEvent.SignInUser)
                        } else {
                            _eventFlow.emit(UiEvent.ShowSnackbar("네트워크 연결을 확인해주세요."))
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        _eventFlow.emit(UiEvent.ShowSnackbar("네트워크 연결을 확인해주세요."))
                    }
                }
            }
        }
    }

    fun kakaoSignIn(){
        viewModelScope.launch {
            signInState.emit(handlekakaoSignIn())
        }
    }

    fun kakaoSignOut(){
        viewModelScope.launch {
            if(handleKakaoSignOut()){
                signInState.emit(false)
            }
        }
    }

    private suspend fun handlekakaoSignIn() : Boolean =
        suspendCoroutine { cont ->
            // 로그인 조합 예제
            // 카카오계정으로 로그인 공통 callback 구성
            // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
            val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                if (error != null) {
                    Log.e(TAG, "카카오계정으로 로그인 실패", error)
                    cont.resume(false)
                } else if (token != null) {
                    Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
                    cont.resume(true)
                }
            }

            // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(appContext)) {
                UserApiClient.instance.loginWithKakaoTalk(appContext) { token, error ->
                    if (error != null) {
                        Log.e(TAG, "카카오톡으로 로그인 실패", error)

                        // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                        // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }

                        // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                        UserApiClient.instance.loginWithKakaoAccount(appContext,
                            callback = callback)
                    } else if (token != null) {
                        Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(appContext, callback = callback)
            }
        }

    private suspend fun handleKakaoSignOut(): Boolean =
        suspendCoroutine { cont ->
            // 로그아웃
            UserApiClient.instance.logout { error ->
                if (error != null) {
                    Log.e(TAG, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
                    cont.resume(false)
                } else {
                    Log.i(TAG, "로그아웃 성공. SDK에서 토큰 삭제됨")
                    cont.resume(true)
                }
            }
        }

    sealed class UiEvent{
        data class ShowSnackbar(val message: String): UiEvent()
        object SignInUser: UiEvent()
    }
}