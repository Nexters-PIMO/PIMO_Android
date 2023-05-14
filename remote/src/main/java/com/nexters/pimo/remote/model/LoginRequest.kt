package com.nexters.pimo.remote.model

import com.nexters.pimo.data.model.ProviderTokenData

data class LoginRequest(
    val identifier: String,
    val provider: String
)

fun ProviderTokenData.toRemote() = LoginRequest(idToken, provider)
