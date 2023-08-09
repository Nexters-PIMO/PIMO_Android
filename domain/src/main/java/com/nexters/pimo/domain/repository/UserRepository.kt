package com.nexters.pimo.domain.repository

import com.nexters.pimo.domain.model.User

interface UserRepository {

    suspend fun validateNickname(nickname: String): Result<Boolean>

    suspend fun validateArchive(archive: String): Result<Boolean>

    suspend fun getMe(): Result<User>
}
