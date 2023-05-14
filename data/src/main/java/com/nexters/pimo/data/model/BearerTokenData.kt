package com.nexters.pimo.data.model

import com.nexters.pimo.domain.model.BearerToken

data class BearerTokenData(
    val accessToken: String,
    val refreshToken: String
) {

    fun isNone() = accessToken.isBlank() && refreshToken.isBlank()

    companion object {
        val NONE = BearerTokenData("", "")
    }
}

fun BearerTokenData.toDomain() = BearerToken(accessToken, refreshToken)
