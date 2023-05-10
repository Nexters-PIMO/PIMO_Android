package com.nexters.pimo.domain.model

data class ProviderToken(
    val provider: String,
    val idToken: String,
    val accessToken: String,
    val refreshToken: String
) {
    companion object {
        private const val PROVIDER_KAKAO = "KAKAO"

        fun kakao(
            idToken: String,
            accessToken: String,
            refreshToken: String
        ) = ProviderToken(
            provider = PROVIDER_KAKAO,
            idToken = idToken,
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }
}
