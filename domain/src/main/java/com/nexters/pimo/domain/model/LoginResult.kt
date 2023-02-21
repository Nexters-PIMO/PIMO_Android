package com.nexters.pimo.domain.model

sealed interface LoginResult {
    data class SignedIn(val user: User) : LoginResult
    object NotSignedUpYet : LoginResult
    object Unspecified : LoginResult
}
