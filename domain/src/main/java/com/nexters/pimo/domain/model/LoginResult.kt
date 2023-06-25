package com.nexters.pimo.domain.model

sealed interface LoginResult {
    data class SignedIn(val token: BearerToken) : LoginResult
    object FailToAutoLogin : LoginResult
    data class NeedToSignUp(val provider: String, val identifier: String) : LoginResult
    object Unspecified : LoginResult
}
