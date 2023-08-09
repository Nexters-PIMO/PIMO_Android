package com.nexters.pimo.remote.model

import com.nexters.pimo.data.model.UserData

data class UserResponse(
    val id: String,
    val nickname: String,
    val archiveName: String,
    val profileImageUrl: String,
    val postCount: Int = 0
)

fun UserResponse.toData() = UserData(
    id, nickname, archiveName, profileImageUrl
)
