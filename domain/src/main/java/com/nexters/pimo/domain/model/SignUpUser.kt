package com.nexters.pimo.domain.model

data class SignUpUser(
    val token: ProviderToken,
    val nickname: String,
    val archiveName: String,
    val profileImageUrl: String
)
