package com.nexters.pimo.data.repository

import com.nexters.pimo.data.model.toData
import com.nexters.pimo.data.model.toDomain
import com.nexters.pimo.data.source.AuthDataSource
import com.nexters.pimo.data.source.TokenDataSource
import com.nexters.pimo.domain.model.LoginResult
import com.nexters.pimo.domain.model.ProviderToken
import com.nexters.pimo.domain.model.SignUpUser
import com.nexters.pimo.domain.repository.AuthRepository
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val tokenDataSource: TokenDataSource
) : AuthRepository {

    override suspend fun login(): Result<LoginResult> = runCatching {
        tokenDataSource.loadToken().getOrThrow().let {
            if (it.isNone()) LoginResult.FailToAutoLogin
            else LoginResult.SignedIn(it.toDomain())
        }
    }

    override suspend fun login(token: ProviderToken): Result<LoginResult> = runCatching {
        authDataSource.login(token.toData()).getOrNull()?.let {
            tokenDataSource.saveToken(it)
            LoginResult.SignedIn(it.toDomain())
        } ?: LoginResult.NeedToSignUp
    }

    override suspend fun logout(): Result<Unit> = authDataSource.logout()

    override suspend fun signUp(user: SignUpUser): Result<Unit> =
        authDataSource.signUp(user.toData())
}
