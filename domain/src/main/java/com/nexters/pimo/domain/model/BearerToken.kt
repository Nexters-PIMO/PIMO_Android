package com.nexters.pimo.domain.model

data class BearerToken(
    val accessToken: String,
    val refreshToken: String
)
