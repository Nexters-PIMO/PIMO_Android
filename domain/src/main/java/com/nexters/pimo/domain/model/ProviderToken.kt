package com.nexters.pimo.domain.model

data class ProviderToken(
    val provider: String,
    val idToken: String
) {
    companion object {
        private const val PROVIDER_KAKAO = "KAKAO"

        fun kakao(idToken: String) = ProviderToken(
            provider = PROVIDER_KAKAO,
            idToken = idToken
        )
    }
}
