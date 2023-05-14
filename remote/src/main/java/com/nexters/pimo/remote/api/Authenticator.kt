package com.nexters.pimo.remote.api

import com.nexters.pimo.data.provider.AccessTokenProvider
import com.nexters.pimo.data.provider.RefreshTokenProvider
import com.nexters.pimo.remote.model.BearerTokenRequest
import com.nexters.pimo.remote.model.BearerTokenResponse
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class Authenticator(
    private val apiService: RefreshApiService,
    private val accessTokenProvider: AccessTokenProvider,
    private val refreshTokenProvider: RefreshTokenProvider,
    private val authenticationListener: AuthenticationListener
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val accessToken = accessTokenProvider.value
        val refreshToken = refreshTokenProvider.value

        if (refreshToken.isBlank()) {
            authenticationListener.onSessionExpired()
            return null
        }

        return refresh(accessToken, refreshToken).fold(
            onSuccess = {
                accessTokenProvider.value = it.accessToken
                refreshTokenProvider.value = it.refreshToken

                AccessTokenInterceptor.from(response.request, it.accessToken)
            },
            onFailure = {
                authenticationListener.onSessionExpired()
                null
            }
        )
    }

    private fun refresh(
        accessToken: String,
        refreshToken: String
    ): Result<BearerTokenResponse> = runBlocking {
        runCatching {
            apiService.refresh(BearerTokenRequest(accessToken, refreshToken))
        }
    }
}
