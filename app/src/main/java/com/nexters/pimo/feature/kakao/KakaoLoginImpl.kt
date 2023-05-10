package com.nexters.pimo.feature.kakao

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

internal class KakaoLoginImpl @Inject constructor() : KakaoLogin {

    /**
     * @param context: Activity context
     */
    override suspend fun login(context: Context): Result<KakaoToken> = runCatching {
        suspendCancellableCoroutine { continuation ->
            val callback: (OAuthToken?, Throwable?) -> Unit = { token, throwable ->
                when {
                    throwable != null -> continuation.resumeWithException(throwable)
                    token != null && continuation.isActive -> {
                        continuation.resume(
                            KakaoToken(
                                token.idToken!!,
                                token.accessToken,
                                token.refreshToken
                            )
                        )
                    }
                }
            }

            val userApiClient = UserApiClient.instance
            if (userApiClient.isKakaoTalkLoginAvailable(context)) {
                userApiClient.loginWithKakaoTalk(context, callback = callback)
            } else {
                userApiClient.loginWithKakaoAccount(context, callback = callback)
            }
        }
    }

    override suspend fun logout(): Result<Unit> = runCatching {
        suspendCoroutine { continuation ->
            UserApiClient.instance.logout { continuation.resume(Unit) }
        }
    }
}
