package com.example.dongbangjupsho

import android.app.Application
import com.google.firebase.FirebaseApp
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class DongBangApp: Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, com.example.dongbangjupsho.BuildConfig.KAKAO_NATIVE_APP_KEY)
    }
}