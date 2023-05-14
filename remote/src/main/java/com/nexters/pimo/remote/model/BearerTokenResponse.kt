package com.nexters.pimo.remote.model

import com.nexters.pimo.data.model.BearerTokenData

data class BearerTokenResponse(
    val accessToken: String,
    val refreshToken: String
) {
    fun toData() = BearerTokenData(accessToken, refreshToken)
}
