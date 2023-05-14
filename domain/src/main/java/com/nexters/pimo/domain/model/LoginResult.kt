package com.nexters.pimo.domain.model

sealed interface LoginResult {
    data class SignedIn(val token: BearerToken) : LoginResult
    object FailToAutoLogin : LoginResult
    object NeedToSignUp : LoginResult
    object Unspecified : LoginResult
}
