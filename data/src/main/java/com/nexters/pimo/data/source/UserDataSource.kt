package com.nexters.pimo.data.source

import com.nexters.pimo.data.model.UserData

interface UserDataSource {

    suspend fun validateNickname(nickname: String): Result<Boolean>

    suspend fun validateArchive(archive: String): Result<Boolean>

    suspend fun getMe(): Result<UserData>
}
