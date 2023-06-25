package com.nexters.pimo.data.source

interface UserDataSource {

    suspend fun validateNickname(nickname: String): Result<Boolean>

    suspend fun validateArchive(archive: String): Result<Boolean>
}