package com.nexters.pimo.remote.model

data class BearerTokenRequest(
    val accessToken: String,
    val refreshToken: String
)
