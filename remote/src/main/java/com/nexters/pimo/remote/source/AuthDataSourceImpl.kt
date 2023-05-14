package com.nexters.pimo.remote.source

import com.nexters.pimo.data.model.BearerTokenData
import com.nexters.pimo.data.model.ProviderTokenData
import com.nexters.pimo.data.model.SignUpData
import com.nexters.pimo.data.source.AuthDataSource
import com.nexters.pimo.remote.api.ApiService
import com.nexters.pimo.remote.model.toRemote
import javax.inject.Inject

internal class AuthDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : AuthDataSource {

    override suspend fun login(token: ProviderTokenData): Result<BearerTokenData> = runCatching {
        apiService.login(token.toRemote()).toData()
    }

    override suspend fun logout(): Result<Unit> = runCatching {
        apiService.logout()
    }

    override suspend fun signUp(user: SignUpData): Result<Unit> = runCatching {
        apiService.signup(user.toRemote())
    }
}
