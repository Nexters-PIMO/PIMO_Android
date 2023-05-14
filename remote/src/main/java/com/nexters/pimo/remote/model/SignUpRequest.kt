package com.nexters.pimo.remote.model

import com.nexters.pimo.data.model.SignUpData

data class SignUpRequest(
    val identifier: String,
    val provider: String,
    val nickname: String,
    val archiveName: String,
    val profileImageUrl: String
)

fun SignUpData.toRemote() = SignUpRequest(
    token.idToken, token.provider, nickname, archiveName, profileImageUrl
)
