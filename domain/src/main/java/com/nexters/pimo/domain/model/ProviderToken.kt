package com.nexters.pimo.domain.model

data class ProviderToken(
    val accessToken: String,
    val refreshToken: String,
    val name: String,
) {
    companion object {
        private const val NAME_KAKAO = "KAKAO"

        fun kakao(accessToken: String, refreshToken: String) = ProviderToken(
            accessToken = accessToken,
            refreshToken = refreshToken,
            name = NAME_KAKAO
        )
    }
}
