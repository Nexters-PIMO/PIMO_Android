package com.nexters.pimo

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // Kakao
        KakaoSdk.init(this, BuildConfig.KAKAO_API_KEY)

    }

}
