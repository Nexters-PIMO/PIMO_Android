package com.nexters.pimo.domain.model

sealed interface LoginResult {
    object Signed : LoginResult
    object Onboard : LoginResult
}
