package com.nexters.pimo.data.model

import com.nexters.pimo.domain.model.User

data class UserData(
    val id: String,
    val nickname: String,
    val archiveName: String,
    val profileImageUrl: String
)

fun UserData.toDomain() = User(
    id = id,
    nickname = nickname,
    archiveName = archiveName,
    profileImageUrl = profileImageUrl,
    posts = emptyList()
)
