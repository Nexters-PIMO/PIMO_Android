package com.nexters.pimo.feature.kakao

data class KakaoToken(
    val idToken: String,
    val kakaoAccessToken: String,
    val kakaoRefreshToken: String
)