package com.nexters.pimo.remote.source

import com.nexters.pimo.data.model.UserData
import com.nexters.pimo.data.source.UserDataSource
import com.nexters.pimo.remote.api.ApiService
import com.nexters.pimo.remote.model.toData
import javax.inject.Inject

internal class UserDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : UserDataSource {

    override suspend fun validateNickname(nickname: String): Result<Boolean> =
        runCatching { apiService.validateNickname(nickname) }

    override suspend fun validateArchive(archive: String): Result<Boolean> =
        runCatching { apiService.validateArchive(archive) }

    override suspend fun getMe(): Result<UserData> =
        runCatching { apiService.getMe().toData() }
}
