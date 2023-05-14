package com.nexters.pimo.data.model

import com.nexters.pimo.domain.model.ProviderToken

data class ProviderTokenData(
    val idToken: String,
    val provider: String
)

fun ProviderToken.toData() = ProviderTokenData(idToken, provider)
