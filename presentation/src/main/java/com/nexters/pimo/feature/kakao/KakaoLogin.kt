package com.nexters.pimo.feature.kakao

import android.content.Context

interface KakaoLogin {

    suspend fun login(context: Context): Result<KakaoToken>

    suspend fun logout(): Result<Unit>
}
