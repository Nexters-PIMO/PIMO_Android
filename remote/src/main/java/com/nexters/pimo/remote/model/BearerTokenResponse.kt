package com.nexters.pimo.remote.model

import com.nexters.pimo.data.model.BearerTokenData

data class BearerTokenResponse(
    val accessToken: String,
    val refreshToken: String
)

fun BearerTokenResponse.toData() = BearerTokenData(accessToken, refreshToken)
