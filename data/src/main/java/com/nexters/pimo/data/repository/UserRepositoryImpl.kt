package com.nexters.pimo.data.repository

import com.nexters.pimo.data.source.UserDataSource
import com.nexters.pimo.domain.repository.UserRepository
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {

    override suspend fun validateNickname(nickname: String): Result<Boolean> =
        userDataSource.validateNickname(nickname)

    override suspend fun validateArchive(archive: String): Result<Boolean> =
        userDataSource.validateArchive(archive)
}
