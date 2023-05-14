package com.nexters.pimo.data.model

import com.nexters.pimo.domain.model.SignUpUser

data class SignUpData(
    val token: ProviderTokenData,
    val nickname: String,
    val archiveName: String,
    val profileImageUrl: String
)

fun SignUpUser.toData() = SignUpData(token.toData(), nickname, archiveName, profileImageUrl)