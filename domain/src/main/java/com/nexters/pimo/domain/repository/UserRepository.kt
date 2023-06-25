package com.nexters.pimo.domain.repository

interface UserRepository {

    suspend fun validateNickname(nickname: String): Result<Boolean>

    suspend fun validateArchive(archive: String): Result<Boolean>
}
